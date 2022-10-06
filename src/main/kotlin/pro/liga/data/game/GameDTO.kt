package pro.liga.data.game

import java.time.LocalDateTime

data class GameDTO(
    val id: Int,
    val tournamentId: Int,
    val ended: Boolean,
    val type: Int,
    val dateTime: LocalDateTime,
    val countSets: Int? = null,
    val countPoints: Int? = null,
    //val sets: List<SetDTO>? = null
)
