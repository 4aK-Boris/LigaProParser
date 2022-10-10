package pro.liga.data.player.rating

import java.time.LocalDate

data class RatingDTO(
    val playerId: Int,
    val rating: Int,
    val date: LocalDate
)
