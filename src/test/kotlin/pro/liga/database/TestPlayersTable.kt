package pro.liga.database

import java.time.LocalDate
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.qualifier.named
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import pro.liga.data.player.PlayerDTO
import pro.liga.database.player.PlayerTransaction
import pro.liga.di.appModule
import kotlin.test.assertEquals


class TestPlayersTable : KoinTest {

    private val playerTransaction by lazy { get<PlayerTransaction>() }

    @Before
    fun before() {
        println("Database connect")
        DataSource.testConnectDatabase()

    }

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(appModule)
    }

    @Test
    fun testInsertOne() {
        val playerDTO = get<PlayerDTO>()
        val playerEntity = playerTransaction.insert(playerDTO = playerDTO)
        val selectPlayerEntity = playerTransaction.getEntity(id = playerDTO.id)

        assertEquals(expected = playerEntity, actual = selectPlayerEntity)

        playerTransaction.delete(id = 1)
    }

    @Test
    fun testInsertTwo() {
        val playerDTO = get<PlayerDTO>(qualifier = named(TWO))
        val playerEntity = playerTransaction.insert(playerDTO = playerDTO)
        val selectPlayerEntity = playerTransaction.getEntity(id = playerDTO.id)

        assertEquals(expected = playerEntity, actual = selectPlayerEntity)

        playerTransaction.delete(id = 2)
    }

    @Test
    fun testInsertThree() {
        val playerDTO = get<PlayerDTO>(qualifier = named(THREE))
        val playerEntity = playerTransaction.insert(playerDTO = playerDTO)
        val selectPlayerEntity = playerTransaction.getEntity(id = playerDTO.id)

        assertEquals(expected = playerEntity, actual = selectPlayerEntity)

        playerTransaction.delete(id = 3)
    }

    @Test
    fun testDeleteAll() {
        val playerOne = get<PlayerDTO>()
        val playerTwo = get<PlayerDTO>(qualifier = named(TWO))
        val playerThree = get<PlayerDTO>(qualifier = named(THREE))

        playerTransaction.insert(playerDTO = playerOne)
        playerTransaction.insert(playerDTO = playerTwo)
        playerTransaction.insert(playerDTO = playerThree)

        var hasPlayerOne = playerTransaction.hasPlayer(id = playerOne.id)
        var hasPlayerTwo = playerTransaction.hasPlayer(id = playerTwo.id)
        var hasPlayerThree = playerTransaction.hasPlayer(id = playerThree.id)

        assertEquals(expected = hasPlayerOne, actual = true)
        assertEquals(expected = hasPlayerTwo, actual = true)
        assertEquals(expected = hasPlayerThree, actual = true)

        playerTransaction.deleteAll()

        hasPlayerOne = playerTransaction.hasPlayer(id = playerOne.id)
        hasPlayerTwo = playerTransaction.hasPlayer(id = playerTwo.id)
        hasPlayerThree = playerTransaction.hasPlayer(id = playerThree.id)

        assertEquals(expected = hasPlayerOne, actual = false)
        assertEquals(expected = hasPlayerTwo, actual = false)
        assertEquals(expected = hasPlayerThree, actual = false)
    }

    @Test
    fun testDeleteById() {
        val player = get<PlayerDTO>()
        playerTransaction.insert(playerDTO = player)
        var hasPlayer = playerTransaction.hasPlayer(id = player.id)
        assertEquals(expected = hasPlayer, actual = true)
        playerTransaction.delete(id = player.id)
        hasPlayer = playerTransaction.hasPlayer(id = player.id)
        assertEquals(expected = hasPlayer, actual = false)
    }

    @Test
    fun testDeleteByDate() {
        val player = get<PlayerDTO>()
        playerTransaction.insert(playerDTO = player)
        var hasPlayer = playerTransaction.hasPlayer(id = player.id)
        assertEquals(expected = hasPlayer, actual = true)
        val date = LocalDate.now().plusYears(1L)
        playerTransaction.delete(date = date)
        hasPlayer = playerTransaction.hasPlayer(id = player.id)
        assertEquals(expected = hasPlayer, actual = false)
    }

    @Test
    fun testUpdate() {
        val oldPlayer = get<PlayerDTO>()
        val newPlayer = get<PlayerDTO>(qualifier = named(TWO)).copy(id = oldPlayer.id)

        playerTransaction.insert(playerDTO = oldPlayer)
        playerTransaction.update(playerDTO = newPlayer)

        val playerEntity = playerTransaction.getEntity(id = oldPlayer.id)

        assertEquals(expected = playerEntity?.equals(newPlayer), actual = true)

        playerTransaction.delete(id = oldPlayer.id)
    }

    companion object {
        private const val TWO = "2"
        private const val THREE = "3"
    }
}