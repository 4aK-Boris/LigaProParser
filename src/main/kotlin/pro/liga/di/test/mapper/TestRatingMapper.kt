package pro.liga.di.test.mapper

import org.koin.dsl.module
import pro.liga.data.player.rating.Rating
import pro.liga.di.Qualifiers.FIRST
import pro.liga.di.Qualifiers.LIST_RATINGS
import pro.liga.di.Qualifiers.SECOND
import pro.liga.di.Qualifiers.THIRD

val testRatingMapper = module {

    single(qualifier = FIRST.qualifier) {
        Rating(idPlayer = 1, rating = "500")
    }

    single(qualifier = SECOND.qualifier) {
        Rating(idPlayer = 2, rating = "750")
    }

    single(qualifier = THIRD.qualifier) {
        Rating(idPlayer = 3, rating = "1000")
    }

    single(LIST_RATINGS.qualifier) {
        listOf<Rating>(
            get(qualifier = FIRST.qualifier),
            get(qualifier = SECOND.qualifier),
            get(qualifier = THIRD.qualifier)
        )
    }
}