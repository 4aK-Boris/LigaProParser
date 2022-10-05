package pro.liga.data.tournament

import com.example.data.game.GameDTO
import com.example.data.tournament.player.TournamentPlayerDTO
import java.time.LocalDateTime

data class TournamentDTO(
    val id: Int,
    val title: String,
    val type: TournamentType,
    val dateTime: LocalDateTime,
    val dayOfTheWeek: DayOfTheWeek,
    val players: List<TournamentPlayerDTO>,
    val games: List<GameDTO>
)
