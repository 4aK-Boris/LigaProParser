package pro.liga.di.test

import org.koin.dsl.module
import pro.liga.di.test.database.testDatabaseModule
import pro.liga.di.test.mapper.testMapperModule

val mainTestModule = module {
    includes(testDatabaseModule, testMapperModule)
}