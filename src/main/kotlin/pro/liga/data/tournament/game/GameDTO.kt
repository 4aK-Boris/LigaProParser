package pro.liga.data.tournament.game

import java.time.LocalDateTime
import pro.liga.data.DTO
import pro.liga.data.tournament.game.set.SetDTO
import pro.liga.database.tournament.game.type.MatchType

data class GameDTO(
    val id: Int,
    val ended: Boolean,
    val matchType: MatchType,
    val dateTime: LocalDateTime,
    val sets: List<SetDTO>? = null
): DTO
