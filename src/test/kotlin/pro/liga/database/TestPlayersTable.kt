package pro.liga.database

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import pro.liga.data.player.PlayerDTO
import pro.liga.database.player.PlayerTransaction
import pro.liga.di.Qualifiers.LIST_PLAYERS_DTO
import pro.liga.di.Qualifiers.LIST_UPDATE_PLAYERS_DTO
import pro.liga.di.appModule
import kotlin.test.assertEquals

class TestPlayersTable : KoinTest {

    private val playerTransaction by lazy { get<PlayerTransaction>() }

    private val listPlayerDTO by lazy { get<List<PlayerDTO>>(qualifier = LIST_PLAYERS_DTO.qualifier) }
    private val listUpdatePlayersDTO by lazy {
        get<List<Pair<PlayerDTO, PlayerDTO>>>(qualifier = LIST_UPDATE_PLAYERS_DTO.qualifier)
    }

    @Before
    fun before() {
        println("Database connect")
        DataSource.testConnectDatabase()
        playerTransaction.deleteAll()
    }

    @After
    fun after() {
        playerTransaction.deleteAll()
    }

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(appModule)
    }

    @Test
    fun testInsert() {

        listPlayerDTO.forEach {

            val playerEntity = playerTransaction.insert(playerDTO = it)

            val selectPlayerEntity = playerTransaction.getEntity(id = it.id)

            assertEquals(expected = playerEntity, actual = selectPlayerEntity)
        }

        playerTransaction.deleteAll()
    }

    @Test
    fun testDeleteAll() {

        listPlayerDTO.forEach {

            playerTransaction.insert(playerDTO = it)

            assertEquals(expected = playerTransaction.hasPlayer(id = it.id), actual = true)
        }

        playerTransaction.deleteAll()

        listPlayerDTO.forEach {
            assertEquals(expected = playerTransaction.hasPlayer(id = it.id), actual = false)

        }

    }

    @Test
    fun testDeleteById() {

        listPlayerDTO.forEach {

            playerTransaction.insert(playerDTO = it)

            assertEquals(expected = playerTransaction.hasPlayer(id = it.id), actual = true)

            playerTransaction.delete(id = it.id)

            assertEquals(expected = playerTransaction.hasPlayer(id = it.id), actual = false)
        }
    }

    @Test
    fun testDeleteByDate() {

        listPlayerDTO.forEach {
            playerTransaction.insert(playerDTO = it)

            assertEquals(expected = playerTransaction.hasPlayer(id = it.id), actual = true)

            playerTransaction.delete(date = it.date.plusMonths(1L))

            assertEquals(expected = playerTransaction.hasPlayer(id = it.id), actual = false)
        }

        playerTransaction.deleteAll()
    }

    @Test
    fun testUpdate() {
        listUpdatePlayersDTO.forEach { playerDTO ->

            playerTransaction.insert(playerDTO = playerDTO.first)

            playerTransaction.update(playerDTO = playerDTO.second)

            val playerEntity = playerTransaction.getEntity(id = playerDTO.first.id)

            assertEquals(expected = playerEntity?.equals(playerDTO.second), actual = true)
        }
        playerTransaction.deleteAll()
    }
}