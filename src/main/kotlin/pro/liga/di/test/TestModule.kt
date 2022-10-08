package pro.liga.di.test

import org.koin.dsl.module

val testModule = module {
    includes(testPlayerModule, testRatingModule, testEndedTournamentModule)
}