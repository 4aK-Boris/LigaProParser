package pro.liga.data.tournament.ended

import com.example.data.tournament.player.EndedTournamentPlayerDTO
import pro.liga.data.game.GameDTO
import java.time.LocalDateTime
import pro.liga.data.tournament.DayOfTheWeek
import pro.liga.data.tournament.League
import pro.liga.data.tournament.TournamentType

data class EndedTournamentDTO(
    val id: Int,
    val league: League,
    val type: TournamentType,
    val dateTime: LocalDateTime,
    val dayOfTheWeek: DayOfTheWeek,
    val players: List<EndedTournamentPlayerDTO>,
    //val games: List<GameDTO>
)
