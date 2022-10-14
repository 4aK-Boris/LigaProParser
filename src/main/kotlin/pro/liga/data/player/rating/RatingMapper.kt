package pro.liga.data.player.rating

import java.time.LocalDate
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.koin.core.logger.Logger

class RatingMapper(private val logger: Logger) {

    fun map(playerId: Int, counter: Int = 1): RatingDTO? {
        try {
            val document = getDocument(id = playerId)
            document.rating()?.let { rating ->
                return RatingDTO(
                    playerId = playerId,
                    rating = rating,
                    date = LocalDate.now()
                )
            }
        } catch (e: Exception) {
            logger.info("Ошибка! playerId = $playerId! ${e.message}")
            if (counter < NUMBER_OF_ATTEMPTS) {
                map(playerId = playerId, counter = counter + 1)
            }
        }
        return null
    }

    private fun getDocument(id: Int) = Jsoup.connect("$URL_LIGA_PRO/$PREFIX$id").get()

    private fun Document.rating(): Short? {
        return this.select("table.user-rating-table")
            .select("td")
            .map { it.text() }
            .chunked(size = 4)
            .firstOrNull { it[LIGA_PRO_NUMBER] == LIGA_PRO }?.let {
                it[RATING_NUMBER].toShort()
            }
    }

    companion object {
        private const val RATING_NUMBER = 2
        private const val LIGA_PRO_NUMBER = 1
        private const val PREFIX = "players/"
        private const val NUMBER_OF_ATTEMPTS = 10
        private const val URL_LIGA_PRO = "https://tt.sport-liga.pro"
        private const val LIGA_PRO = "ЛигаПРО"
    }
}
