package pro.liga.di.test

import java.time.LocalDate
import org.koin.core.qualifier.named
import org.koin.dsl.module
import pro.liga.data.rating.RatingDTO

private const val ONE = "1"
private const val TWO = "2"
private const val THREE = "3"

fun testRatingModule() = module {

    single(qualifier = named(ONE)) {
        RatingDTO(
            playerId = 1,
            rating = 500,
            date = LocalDate.now()
        )
    }

    single(qualifier = named(TWO)) {
        RatingDTO(
            playerId = 2,
            rating = 750,
            date = LocalDate.now()
        )
    }

    single(qualifier = named(THREE)) {
        RatingDTO(
            playerId = 3,
            rating = 1000,
            date = LocalDate.now()
        )
    }
}