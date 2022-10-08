package pro.liga.database

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.qualifier.named
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import pro.liga.data.player.PlayerDTO
import pro.liga.data.rating.RatingDTO
import pro.liga.database.player.PlayerTransaction
import pro.liga.database.player.rating.RatingTransaction
import pro.liga.di.appModule
import kotlin.test.assertEquals

class TestRatingTable : KoinTest {

    private val playerTransaction by lazy { get<PlayerTransaction>() }
    private val ratingTransaction by lazy { get<RatingTransaction>() }

    private val playerOne by lazy { get<PlayerDTO>() }
    private val playerTwo by lazy { get<PlayerDTO>(qualifier = named(TWO)) }
    private val playerThree by lazy { get<PlayerDTO>(qualifier = named(THREE)) }

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
    fun testInsertOneWithEntity() {
        val listRatingDTO = get<List<RatingDTO>>()
        val player = playerTransaction.insert(playerDTO = playerOne)
        listRatingDTO.forEach { ratingTransaction.insert(ratingDTO = it, playerEntity = player) }
        val listRatingEntity = playerTransaction.ratings(playerEntity = player)
        listRatingDTO.zip(listRatingEntity) { dto, entity ->
            val playerEntity = ratingTransaction.player(ratingEntity = entity)
            assertEquals(expected = playerEntity.id.value, actual = dto.playerId)
            assertEquals(expected = entity.rating, actual = dto.rating)
            assertEquals(expected = entity.date, actual = dto.date)
        }
    }

    @Test
    fun testInsertTwoWithEntity() {
        val listRatingDTO = get<List<RatingDTO>>(qualifier = named(name = TWO))
        val player = playerTransaction.insert(playerDTO = playerTwo)
        listRatingDTO.forEach { ratingTransaction.insert(ratingDTO = it, playerEntity = player) }
        val listRatingEntity = playerTransaction.ratings(playerEntity = player)
        listRatingDTO.zip(listRatingEntity) { dto, entity ->
            val playerEntity = ratingTransaction.player(ratingEntity = entity)
            assertEquals(expected = playerEntity.id.value, actual = dto.playerId)
            assertEquals(expected = entity.rating, actual = dto.rating)
            assertEquals(expected = entity.date, actual = dto.date)
        }
    }

    @Test
    fun testInsertThreeWithEntity() {
        val listRatingDTO = get<List<RatingDTO>>(qualifier = named(name = THREE))
        val player = playerTransaction.insert(playerDTO = playerThree)
        listRatingDTO.forEach { ratingTransaction.insert(ratingDTO = it, playerEntity = player) }
        val listRatingEntity = playerTransaction.ratings(playerEntity = player)
        listRatingDTO.zip(listRatingEntity) { dto, entity ->
            val playerEntity = ratingTransaction.player(ratingEntity = entity)
            assertEquals(expected = playerEntity.id.value, actual = dto.playerId)
            assertEquals(expected = entity.rating, actual = dto.rating)
            assertEquals(expected = entity.date, actual = dto.date)
        }
    }

    @Test
    fun testDelete() {
        playerTransaction.deleteAll()

        val ratingsOne = playerTransaction.ratings(id = playerOne.id)
        val ratingsTwo = playerTransaction.ratings(id = playerTwo.id)
        val ratingsThree = playerTransaction.ratings(id = playerThree.id)

        assertEquals(expected = ratingsOne, actual = null)
        assertEquals(expected = ratingsTwo, actual = null)
        assertEquals(expected = ratingsThree, actual = null)
    }

    @Test
    fun testInsertOneWithId() {
        playerTransaction.insert(playerDTO = playerOne)
        val listRatingDTO = get<List<RatingDTO>>()
        val listRatingEntity = listRatingDTO.map { ratingTransaction.insert(ratingDTO = it) }
        listRatingDTO.zip(listRatingEntity) { dto, entity ->
            val playerEntity = entity?.let { ratingTransaction.player(ratingEntity = it) }
            assertEquals(expected = playerEntity?.id?.value, actual = dto.playerId)
            assertEquals(expected = entity?.rating, actual = dto.rating)
            assertEquals(expected = entity?.date, actual = dto.date)
        }
    }

    @Test
    fun testInsertTwoWithId() {
        playerTransaction.insert(playerDTO = playerTwo)
        val listRatingDTO = get<List<RatingDTO>>(qualifier = named(name = TWO))
        val listRatingEntity = listRatingDTO.map { ratingTransaction.insert(ratingDTO = it) }
        listRatingDTO.zip(listRatingEntity) { dto, entity ->
            val playerEntity = entity?.let { ratingTransaction.player(ratingEntity = it) }
            assertEquals(expected = playerEntity?.id?.value, actual = dto.playerId)
            assertEquals(expected = entity?.rating, actual = dto.rating)
            assertEquals(expected = entity?.date, actual = dto.date)
        }
    }

    @Test
    fun testInsertThreeWithId() {
        playerTransaction.insert(playerDTO = playerThree)
        val listRatingDTO = get<List<RatingDTO>>(qualifier = named(name = THREE))
        val listRatingEntity = listRatingDTO.map { ratingTransaction.insert(ratingDTO = it) }
        listRatingDTO.zip(listRatingEntity) { dto, entity ->
            val playerEntity = entity?.let { ratingTransaction.player(ratingEntity = it) }
            assertEquals(expected = playerEntity?.id?.value, actual = dto.playerId)
            assertEquals(expected = entity?.rating, actual = dto.rating)
            assertEquals(expected = entity?.date, actual = dto.date)
        }
    }

    companion object {
        private const val TWO = "2"
        private const val THREE = "3"
    }
}