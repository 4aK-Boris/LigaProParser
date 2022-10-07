package pro.liga.di

import org.koin.dsl.module
import pro.liga.di.test.testPlayerModule

val appModule = module {
    includes(testPlayerModule())
}