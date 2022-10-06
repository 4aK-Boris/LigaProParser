package pro.liga.data.tournament.ended

import com.example.data.game.GameDTO
import com.example.data.tournament.player.TournamentPlayerDTO
import java.time.LocalDateTime
import pro.liga.data.tournament.DayOfTheWeek
import pro.liga.data.tournament.TournamentType

data class EndedTournamentDTO(
    val id: Int,
    val title: String,
    val type: TournamentType,
    val dateTime: LocalDateTime,
    val dayOfTheWeek: DayOfTheWeek,
    val players: List<TournamentPlayerDTO>,
    val games: List<GameDTO>
)
