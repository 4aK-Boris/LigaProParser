package pro.liga.di

import org.koin.dsl.module
import pro.liga.di.test.testModule
import pro.liga.di.test.testPlayerModule

val appModule = module {
    includes(testModule, transactionModule)
}