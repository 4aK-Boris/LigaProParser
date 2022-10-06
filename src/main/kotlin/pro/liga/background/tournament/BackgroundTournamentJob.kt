package pro.liga.background.tournament

import io.ktor.util.logging.Logger
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import pro.liga.data.tournament.ended.EndedTournament

class BackgroundEndedTournamentJob(private val log: Logger) {

    fun addEndedTournament(id: Int, counter: Int = 1): Boolean {
        try {
            val document = id.document()
            val typeString = document.typeTournament()
            val title = document.tournamentTitle()
            val (dayOfTheWeek, date, time) = document.date()
            val playersInfo = document.playersInfo()
            val isEnded = playersInfo.tournamentIsEnded()
            if (!isEnded) return false
            val players = playersInfo.players()
            val games = document.games()
            val tournament = EndedTournament(
                id = id,
                title = title,
                type = typeString,
                date = date,
                time = time,
                dayOfTheWeek = dayOfTheWeek,
                players = players,
                games = games
            )
        } catch (e: Exception) {
            log.info("Ошибка! Турнир №$id, попытка №$counter")
            addEndedTournament(id, counter + 1)
        }
        return true
    }

    private fun Int.document() = Jsoup.connect("$URL_LIGA_PRO/tours/$this").get()

    private fun Document.tournamentTitle() = this.select("div.breadcrumbs").select("li.active").text()
    private fun Document.typeTournament() = this.select("div.desc-item")[0].text()

    private fun Document.date(): Triple<String, String, String> {
        val dateBlock = this.select("div.node").select("div.date")
        val dayOfTheWeek = dateBlock.select("span.time")[0].text()
        val dateTime = dateBlock.select("span.day")
        val time = dateTime[1].text()
        val date = dateTime[0].text()
        return Triple(dayOfTheWeek, date, time)
    }

    private fun Document.playersInfo() = this.select("div.item.overflow-auto").select("td.text-nowrap")

    private fun Elements.tournamentIsEnded() = this.select("span").count {
        it.hasClass(MEDAL)
    } == COUNT_MEDALS

    private fun Elements.players() =
        this.select("a").map { it.attr("href") }

    private fun Document.games() =
        this.select("table.games_list").select("a.undr").map { it.attr("href") to it.text() }

    companion object {
        private const val URL_LIGA_PRO = "https://tt.sport-liga.pro"
        private const val MEDAL = "medal"
        private const val COUNT_MEDALS = 3
    }
}