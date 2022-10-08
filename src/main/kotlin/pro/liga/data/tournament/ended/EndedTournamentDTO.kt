package pro.liga.data.tournament.ended

import java.time.LocalDateTime
import pro.liga.data.tournament.DayOfTheWeek
import pro.liga.data.tournament.League
import pro.liga.data.tournament.TournamentType

data class EndedTournamentDTO(
    val id: Int,
    val league: League,
    val tournamentType: TournamentType,
    val dateTime: LocalDateTime,
    val dayOfTheWeek: DayOfTheWeek,
    //val players: List<EndedTournamentPlayerDTO>,
    //val games: List<GameDTO>
)
