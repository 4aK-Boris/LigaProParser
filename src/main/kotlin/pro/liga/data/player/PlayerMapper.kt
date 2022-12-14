package pro.liga.data.player

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.koin.core.component.KoinComponent
import org.koin.core.logger.Logger
import pro.liga.settings.Settings

class PlayerMapper(private val logger: Logger) : KoinComponent {

    fun map(link: String): Player? {
        val id = link.removePrefix(prefix = PREFIX).toInt()
        return map(id = id)
    }

    fun map(id: Int, counter: Int = 1): Player? {
        try {
            val document = getDocument(id = id)
            document.checkAvailabilityPlayer()?.let { name ->
                document.info()?.let { info ->
                    return Player(
                        id = id,
                        name = name,
                        info = info
                    )
                }
            }
        } catch (e: Exception) {
            logger.info("Ошибка! id = $id! ${e.message}")
            if (counter < NUMBER_OF_ATTEMPTS) {
                map(id = id, counter = counter + 1)
            }
        }
        return null
    }

    fun map(player: Player): PlayerDTO? {
        val (firstName, lastName, patronymic) = getName(player.name)
        val rank = player.info[RANK_NUMBER].toShort()
        val rating = player.info[RATING_NUMBER].toShort()
        getDate(date = player.info[DATE_NUMBER])?.let { date ->
            return PlayerDTO(
                firstName = firstName,
                lastName = lastName,
                patronymic = patronymic,
                rank = rank,
                rating = rating,
                date = date,
                id = player.id
            )
        }
        return null
    }

    private fun getName(name: String): Triple<String, String, String?> {
        val str = name.split(' ')
        val lastName = str[0]
        val firstName = str[1]
        val patronymic: String? = str.getOrNull(index = 2)
        return Triple(firstName, lastName, patronymic)
    }

    private fun getDate(date: String): LocalDate? {
        val storageTime = Settings.settings.delaySettings.storageTime.toLong()
        if (date.isNotBlank()) {
            LocalDate.parse(date, formatter).let {
                if (!LocalDate.now().minusYears(storageTime).isAfter(it)) {
                    return it
                }
            }
        }
        return null
    }

    private fun Document.checkAvailabilityPlayer(): String? {
        val name = this.name()
        return if (name == PAGE_NOT_FOUND) null else name
    }


    private fun getDocument(id: Int) = Jsoup.connect("$URL_LIGA_PRO/$PREFIX$id").get()

    private fun Document.name() = this.select("div.breadcrumbs").select("li.active").text()

    private fun Document.info(): List<String>? {
        this.select("table.user-rating-table")
            .select("td")
            .map { it.text() }
            .chunked(size = 4)
            .firstOrNull { it[LIGA_PRO_NUMBER] == LIGA_PRO }?.let {
                if (incorrectInfo(info = it)) return it
            }
        return null
    }

    private fun incorrectInfo(info: List<String>) = info.fold(initial = info.isNotEmpty()) { value, item ->
        value && item.isNotBlank() && !(item.isDigit() && item.toInt() == 0)
    }

    private fun String.isDigit() = this.fold(initial = true) { value, char ->
        value && char.isDigit()
    }

    companion object {
        private const val DATE_NUMBER = 3
        private const val RANK_NUMBER = 0
        private const val RATING_NUMBER = 2
        private const val LIGA_PRO_NUMBER = 1
        private const val PREFIX = "players/"
        private const val NUMBER_OF_ATTEMPTS = 10
        private const val URL_LIGA_PRO = "https://tt.sport-liga.pro"
        private const val PAGE_NOT_FOUND = "Страница не найдена"
        private const val LIGA_PRO = "ЛигаПРО"

        private val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    }
}
