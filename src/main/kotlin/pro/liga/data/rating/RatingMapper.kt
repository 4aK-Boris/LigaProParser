package pro.liga.data.rating

import java.time.LocalDate

class RatingMapper {
    fun map(rating: Rating): RatingDTO {
        return RatingDTO(
            idPlayer = rating.idPlayer,
            rating = rating.rating.toInt(),
            date = LocalDate.now()
        )
    }
}
