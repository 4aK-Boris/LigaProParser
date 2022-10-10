package pro.liga.di.test.database

import org.koin.dsl.module
import pro.liga.di.test.testPlayerModule
import pro.liga.di.test.testTournamentModule
import pro.liga.di.test.testTournamentPlayerModule

val testDatabaseModule = module {
    includes(testRatingModule, testPlayerModule, testTournamentModule, testTournamentPlayerModule)
}