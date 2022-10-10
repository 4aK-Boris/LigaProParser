package pro.liga.di.test.mapper

import org.koin.dsl.module

val testMapperModule = module {
    includes(testPlayerMapper, testRatingMapper)
}