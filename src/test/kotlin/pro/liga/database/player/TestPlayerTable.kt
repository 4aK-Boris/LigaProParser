package pro.liga.database.player

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.qualifier.named
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito
import pro.liga.data.player.PlayerDTO
import pro.liga.database.DataSource
import pro.liga.di.appModule
import kotlin.test.assertEquals


class TestPlayerTable : KoinTest {

    @Before
    fun init() {
        println("Database connect")
        DataSource.connectDatabase()
    }

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(appModule)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Test
    fun testInsert() {
        val playerDTO: PlayerDTO = get(qualifier = named(ONE))
        val playerEntity = PlayerTransaction.insert(playerDTO = playerDTO)
        val selectPlayerEntity = PlayerTransaction.hasPlayerEntity(id = playerDTO.id)

        assertEquals(expected = playerEntity.id.value, actual = selectPlayerEntity?.id?.value)
    }

    companion object {
        private const val ONE = "1"
        private const val TWO = "2"
        private const val THREE = "3"
    }
}