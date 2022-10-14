package pro.liga.di.test.database

import org.koin.dsl.module

val testDatabaseModule = module {
    includes(testRatingModule, testPlayerModule, testTournamentModule, testTournamentPlayerModule)
}