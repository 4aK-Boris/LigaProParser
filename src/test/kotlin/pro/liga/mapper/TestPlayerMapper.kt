package pro.liga.mapper

import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import pro.liga.data.player.Player
import pro.liga.data.player.PlayerDTO
import pro.liga.data.player.PlayerMapper
import pro.liga.di.Qualifiers.*
import pro.liga.di.appModule
import kotlin.test.assertEquals

class TestPlayerMapper : KoinTest {

    private val playerMapper by lazy { get<PlayerMapper>() }

    private val playersId by lazy { get<List<Int>>(qualifier = LIST_PLAYERS_ID.qualifier) }
    private val players by lazy { get<List<Player?>>(qualifier = LIST_PLAYERS.qualifier) }
    private val playersMapper by lazy { get<List<Player>>(qualifier = LIST_PLAYERS_MAPPER.qualifier) }
    private val playersDTOMapper by lazy { get<List<PlayerDTO?>>(qualifier = LIST_PLAYERS_DTO_MAPPER.qualifier) }

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(appModule)
    }

    @Test
    fun testMapperPlayer() {
        playersId.zip(players) { id, player ->

            val newPlayer = playerMapper.map(id = id)

            assertEquals(expected = newPlayer, player)
        }
    }

    @Test
    fun testMapperPlayerDTO() {
        playersMapper.zip(playersDTOMapper) { player, playerDTO ->

            val newPlayerDTO = playerMapper.map(player = player)

            assertEquals(expected = newPlayerDTO, actual = playerDTO)
        }
    }
}