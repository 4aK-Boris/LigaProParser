package pro.liga.data.player.rating

import java.time.LocalDate
import pro.liga.data.DTO

data class RatingDTO(
    val playerId: Int,
    val rating: Int,
    val date: LocalDate
): DTO
