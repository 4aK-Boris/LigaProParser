package pro.liga.data.player.rating

import java.time.LocalDate

class RatingMapper {
    fun map(rating: Rating): RatingDTO {
        return RatingDTO(
            playerId = rating.idPlayer,
            rating = rating.rating.toInt(),
            date = LocalDate.now()
        )
    }
}
