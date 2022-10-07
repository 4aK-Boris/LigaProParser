package pro.liga

import java.time.LocalDate
import org.jetbrains.exposed.sql.transactions.transaction
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.junit.Test
import pro.liga.data.tournament.League.LEAGUE_800_900
import pro.liga.database.DataSource
import pro.liga.database.player.rating.RatingEntity
import pro.liga.database.tournament.ended.EndedTournamentEntity
import pro.liga.database.tournament.ended.EndedTournamentModel

class ApplicationTest {

    @Test
    fun testInt() {
        val str = "hhgg"
        val value = str.toIntOrNull()
        println(value)
    }

    @Test
    fun testEndedTournamentTable() {
        DataSource.connectDatabase()
//        val endedTournamentDTO = EndedTournamentDTO(
//            id = 2,
//            league = LEAGUE_800_900,
//            type = A6,
//            dateTime = LocalDateTime.now(),
//            dayOfTheWeek = FRIDAY
//        )
//        EndedTournamentTransaction.insert(endedTournamentDTO = endedTournamentDTO)
        transaction {
            val data = EndedTournamentEntity.find { EndedTournamentModel.league eq LEAGUE_800_900 }
            data.forEach {
                println(it)
            }
        }
    }

    @Test
    fun testRatingTable() {
        DataSource.connectDatabase()
//        val ratingDTO = RatingDTO(
//            playerId = 3,
//            date = LocalDate.now(),
//            rating = 500,
//        )
//        val scope = CoroutineScope(Dispatchers.IO)
//        val job = scope.launch {
//            RatingModel.insert(ratingDTO)
//        }
//        job.start()
//        while (job.isActive) {
//            val k = 0
//        }
//        transaction {
//            val k = RatingEntity.new(id = 5L) {
//                player = 3
//                date = LocalDate.now()
//                rating = 500
//            }
//            println(k)
//        }
    }

    @Test
    fun testTournament() {
        val id = 20166
        val document = id.document()
        //val typeString = document.typeTournament()
        //val (dayOfTheWeek, date, time) = document.date()
        val playersInfo = document.playersInfo()
        val tournamentIsEnded = playersInfo.tournamentIsEnded()
        val players = playersInfo.players()
        println(players)
        println(document.tournamentTitle())
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
    } == 3

    private fun Elements.players() =
        this.select("a").map { it.attr("href") }

    private fun Document.games() =
        this.select("table.games_list").select("a.undr").map { it.attr("href") to it.text() }

    companion object {
        private const val URL_LIGA_PRO = "https://tt.sport-liga.pro"
        private const val MEDAL = "medal"
    }
}