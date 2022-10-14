package pro.liga.data.player

import java.time.LocalDate
import pro.liga.data.DTO
import pro.liga.data.player.rating.RatingDTO

data class PlayerDTO(
    val firstName: String,
    val lastName: String,
    val patronymic: String?,
    val rank: Short,
    val date: LocalDate,
    val id: Int,
    val rating: Short
): DTO {

    val ratingDTO: RatingDTO
        get() = RatingDTO(
            playerId = id,
            date = date,
            rating = rating
        )
}
