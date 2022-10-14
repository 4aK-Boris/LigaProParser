package pro.liga.data.tournament

import pro.liga.data.player.tournament.TournamentPlayerDTO
import java.time.LocalDateTime
import pro.liga.data.DTO
import pro.liga.data.tournament.options.DayOfTheWeek
import pro.liga.data.tournament.options.League
import pro.liga.data.tournament.options.Room
import pro.liga.data.tournament.options.Type

data class TournamentDTO(
    val id: Int,
    val league: League,
    val room: Room,
    val type: Type,
    val dateTime: LocalDateTime,
    val dayOfTheWeek: DayOfTheWeek,
    val players: List<TournamentPlayerDTO>,
    //val games: List<GameDTO>
): DTO
