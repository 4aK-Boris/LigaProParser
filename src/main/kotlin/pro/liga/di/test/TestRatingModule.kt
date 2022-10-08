package pro.liga.di.test

import java.time.LocalDate
import org.koin.core.qualifier.named
import org.koin.dsl.module
import pro.liga.data.rating.RatingDTO

private const val TWO = "2"
private const val THREE = "3"

private const val FIRST = "first"
private const val SECOND = "second"
private const val THIRD = "third"

val testRatingModule = module {

    single() {
        val ratingDTOFirst = get<RatingDTO>(qualifier = named(FIRST))
        val ratingDTOSecond = copyRatingDTO(ratingDTO = ratingDTOFirst)
        val ratingDTOThird = copyRatingDTO(ratingDTO = ratingDTOSecond)
        listOf(ratingDTOFirst, ratingDTOSecond, ratingDTOThird)
    }

    single(qualifier = named(TWO)) {
        val ratingDTOFirst = get<RatingDTO>(qualifier = named(SECOND))
        val ratingDTOSecond = copyRatingDTO(ratingDTO = ratingDTOFirst)
        val ratingDTOThird = copyRatingDTO(ratingDTO = ratingDTOSecond)
        listOf(ratingDTOFirst, ratingDTOSecond, ratingDTOThird)
    }

    single(qualifier = named(THREE)) {
        val ratingDTOFirst = get<RatingDTO>(qualifier = named(THIRD))
        val ratingDTOSecond = copyRatingDTO(ratingDTO = ratingDTOFirst)
        val ratingDTOThird = copyRatingDTO(ratingDTO = ratingDTOSecond)
        listOf(ratingDTOFirst, ratingDTOSecond, ratingDTOThird)
    }

    single(qualifier = named(name = FIRST)) {
        RatingDTO(
            playerId = 1,
            rating = 500,
            date = LocalDate.now()
        )
    }

    single(qualifier = named(name = SECOND)) {
        RatingDTO(
            playerId = 2,
            rating = 750,
            date = LocalDate.now()
        )
    }

    single(qualifier = named(name = THIRD)) {
        RatingDTO(
            playerId = 3,
            rating = 1000,
            date = LocalDate.now()
        )
    }
}

private fun copyRatingDTO(ratingDTO: RatingDTO) =
    ratingDTO.copy(rating = ratingDTO.rating + 100, date = ratingDTO.date.plusMonths(1L))