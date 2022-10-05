package pro.liga.data.player

import java.time.LocalDate
import pro.liga.data.rating.RatingDTO

data class PlayerDTO(
    val firstName: String,
    val lastName: String,
    val patronymic: String?,
    val rank: Int,
    val date: LocalDate,
    val id: Int,
    val ratingDTO: RatingDTO
)
