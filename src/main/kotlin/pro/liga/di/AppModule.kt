package pro.liga.di

import org.koin.dsl.module
import pro.liga.di.test.mainTestModule

val appModule = module {
    includes(transactionModule, mainTestModule, mapperModule)
}