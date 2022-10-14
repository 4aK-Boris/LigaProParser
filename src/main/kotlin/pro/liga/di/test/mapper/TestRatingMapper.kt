package pro.liga.di.test.mapper

import java.time.LocalDate
import org.koin.dsl.module
import pro.liga.data.player.rating.RatingDTO
import pro.liga.di.Qualifiers.*

val testRatingMapper = module {

    single(qualifier = LIST_RATINGS_ID.qualifier) {
        listOf(357, 6618, 791)
    }

    single(qualifier = LIST_RATINGS_DTO_MAPPER.qualifier) {
        val date = LocalDate.now()
        listOf(
            RatingDTO(
                playerId = 357,
                rating = 1265,
                date = date
            ),
            RatingDTO(
                playerId = 6618,
                rating = 267,
                date = date
            ),
            RatingDTO(
                playerId = 791,
                rating = 753,
                date = date
            )
        )
    }
}