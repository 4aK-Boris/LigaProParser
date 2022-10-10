package pro.liga.mapper

import org.junit.Rule
import org.koin.test.KoinTestRule
import pro.liga.di.appModule

class TestPlayerMapper {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(appModule)
    }
}