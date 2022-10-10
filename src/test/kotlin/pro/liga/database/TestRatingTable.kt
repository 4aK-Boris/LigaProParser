package pro.liga.database

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import pro.liga.data.player.PlayerDTO
import pro.liga.data.player.rating.RatingDTO
import pro.liga.database.player.PlayerTransaction
import pro.liga.database.player.rating.RatingTransaction
import pro.liga.di.Qualifiers.LIST_PLAYER_DTO_WITH_RATING_DTO
import pro.liga.di.appModule
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestRatingTable : KoinTest {

    private val playerTransaction by lazy { get<PlayerTransaction>() }
    private val ratingTransaction by lazy { get<RatingTransaction>() }

    private val listPlayerDTOWithRatingDTO by lazy {
        get<List<Pair<PlayerDTO, List<RatingDTO>>>>(LIST_PLAYER_DTO_WITH_RATING_DTO.qualifier)
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
    fun testInsertWithEntity() {
        listPlayerDTOWithRatingDTO.forEach { (playerDTO, playerRatings) ->

            val player = playerTransaction.insert(playerDTO = playerDTO)

            playerRatings.forEach {
                ratingTransaction.insert(ratingDTO = it, playerEntity = player)
            }

            val listRatingEntity = playerTransaction.ratings(playerEntity = player)

            playerRatings.zip(listRatingEntity) { dto, entity ->
                assertTrue(actual = entity.equals(dto))
            }
        }
        playerTransaction.deleteAll()
    }


    @Test
    fun testDelete() {

        listPlayerDTOWithRatingDTO.forEach { (playerDTO, playerRatings) ->

            val player = playerTransaction.insert(playerDTO = playerDTO)

            playerRatings.forEach {
                ratingTransaction.insert(ratingDTO = it, playerEntity = player)
            }
        }

        playerTransaction.deleteAll()

        listPlayerDTOWithRatingDTO.forEach { (playerDTO, _) ->
            assertEquals(expected = playerTransaction.ratings(id = playerDTO.id), actual = null)
        }
    }

    @Test
    fun testInsertWithId() {

        listPlayerDTOWithRatingDTO.forEach { (playerDTO, playerRatings) ->

            playerTransaction.insert(playerDTO = playerDTO)

            playerRatings.forEach {
                ratingTransaction.insert(ratingDTO = it)
            }

            val listRatingEntity = playerRatings.map { ratingTransaction.insert(ratingDTO = it) }

            playerRatings.zip(listRatingEntity) { dto, entity ->
                assertTrue(actual = entity?.equals(dto) ?: false)
            }
        }

        playerTransaction.deleteAll()
    }
}