package pro.liga.data.tournament.ended

import com.example.data.game.GameMapper
import pro.liga.data.tournament.ended.player.EndedTournamentPlayer
import com.example.data.tournament.player.EndedTournamentPlayerDTO
import com.example.data.tournament.player.EndedTournamentPlayerMapper
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import org.jsoup.Jsoup
import pro.liga.data.game.Game
import pro.liga.data.tournament.DayOfTheWeek
import pro.liga.data.tournament.League
import pro.liga.data.tournament.Month
import pro.liga.data.tournament.TournamentType

class EndedTournamentMapper {
    fun map(endedTournament: EndedTournament): EndedTournamentDTO? {
        val type = TournamentType.getType(endedTournament.type)
        val league = League.getLeague(type = endedTournament.title) ?: return null
        val dateTime = getDateTime(date = endedTournament.date, time = endedTournament.time) ?: return null
        val dayOfTheWeek = DayOfTheWeek.getDay(endedTournament.dayOfTheWeek)
        val players = endedTournament.players.mapIndexed { index, player ->
            getEndedTournamentPlayerDTO(player = player, index = index, tournamentId = endedTournament.id)
        }
        val games = endedTournament.games.map {
            getGameDTO(link = it.first, time = it.second, tournamentId = endedTournament.id, dateTime = dateTime)
        }

        return EndedTournamentDTO(
            id = endedTournament.id,
            type = type,
            league = league,
            dateTime = dateTime,
            dayOfTheWeek = dayOfTheWeek,
            players = players,
            //games = games
        )
    }

    private fun getGameDTO(link: String, time: String, tournamentId: Int, dateTime: LocalDateTime): Unit {
        val document = Jsoup.connect("$URL_LIGA_PRO/$link").get()
        val players = document.select("div.game_info").select("div.player")
            .map { it.selectFirst("a")?.attr("href")?.removePrefix("players/")?.toInt() ?: 0 }
        val points = document.select("div.members").select("table.points").map {
            val set = it.select("tr")
            val setPlayer = set[0].select("td").drop(2)
            val setList = setPlayer.map { p -> if (p.className() == "win") 1 else 2 }
            setList
        }
        var dateTimeGame = LocalDateTime.of(dateTime.toLocalDate(), LocalTime.parse(time, formatter))
        if (dateTimeGame.isBefore(dateTime)) dateTimeGame = dateTimeGame.plusDays(1)
        val game = Game(
            link = link,
            tournamentId = tournamentId,
            dateTime = dateTimeGame,
            players = players[0] to players[1],
            listSets = points
        )
        return gameMapper.map(game)
    }

    private fun checkDate(date: LocalDate) = LocalDate.now().minusYears(1).isAfter(date)

    private fun getDateTime(date: String, time: String): LocalDateTime? {
        val dateList = date.split(' ')
        val monthNumber = Month.getMonthNumber(dateList[1])
        val localDate = LocalDate.of(dateList[2].toInt(), monthNumber, dateList[0].toInt())
        if (checkDate(localDate)) return null
        val localTime = LocalTime.parse(time, formatter)
        return LocalDateTime.of(localDate, localTime)
    }

    private fun getEndedTournamentPlayerDTO(player: String, index: Int, tournamentId: Int): EndedTournamentPlayerDTO {
        val endedTournamentPlayer = EndedTournamentPlayer(
            index = index,
            link = player,
            tournamentId = tournamentId
        )
        return endedTournamentPlayerMapper.map(endedTournamentPlayer)
    }

    companion object {

        private const val URL_LIGA_PRO = "https://tt.sport-liga.pro"

        private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        private val endedTournamentPlayerMapper = EndedTournamentPlayerMapper()
        private val gameMapper = GameMapper()
    }
}