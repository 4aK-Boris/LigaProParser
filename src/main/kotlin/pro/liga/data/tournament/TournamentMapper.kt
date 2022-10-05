package pro.liga.data.tournament

import com.example.URL_LIGA_PRO
import com.example.data.game.Game
import com.example.data.game.GameDTO
import com.example.data.game.GameMapper
import com.example.data.tournament.player.TournamentPlayer
import com.example.data.tournament.player.TournamentPlayerDTO
import com.example.data.tournament.player.TournamentPlayerMapper
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import org.jsoup.Jsoup

class TournamentMapper {
    fun map(tournament: Tournament): TournamentDTO? {
        val type = TournamentType.getType(tournament.type)
        val dateTime = getDateTime(date = tournament.date, time = tournament.time) ?: return null
        val dayOfTheWeek = DayOfTheWeek.getDay(tournament.dayOfTheWeek)
        val players = tournament.players.mapIndexed { index, player ->
            getTournamentsPlayerDTO(player = player, index = index, tournamentId = tournament.id)
        }
        val games = tournament.games.map {
            getGameDTO(link = it.first, time = it.second, tournamentId = tournament.id, dateTime = dateTime)
        }

        return TournamentDTO(
            id = tournament.id,
            type = type,
            title = tournament.title,
            dateTime = dateTime,
            dayOfTheWeek = dayOfTheWeek,
            players = players,
            games = games
        )
    }

    private fun getGameDTO(link: String, time: String, tournamentId: Int, dateTime: LocalDateTime): GameDTO {
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

    private fun getTournamentsPlayerDTO(player: String, index: Int, tournamentId: Int): TournamentPlayerDTO {
        val tournamentPlayer = TournamentPlayer(
            index = index,
            link = player,
            tournamentId = tournamentId
        )
        return tournamentPlayerMapper.map(tournamentPlayer)
    }

    companion object {
        private val formatter = DateTimeFormatter.ofPattern("HH:mm")

        private val tournamentPlayerMapper = TournamentPlayerMapper()

        private val gameMapper = GameMapper()
    }
}