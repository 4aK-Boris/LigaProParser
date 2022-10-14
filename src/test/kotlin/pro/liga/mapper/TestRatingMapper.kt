package pro.liga.mapper

import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import pro.liga.data.player.rating.RatingDTO
import pro.liga.data.player.rating.RatingMapper
import pro.liga.di.Qualifiers.*
import pro.liga.di.appModule
import kotlin.test.assertEquals

class TestRatingMapper: KoinTest {

    private val listRatingsId by lazy { get<List<Int>>(qualifier = LIST_RATINGS_ID.qualifier) }
    private val listRatingsDTO by lazy { get<List<RatingDTO>>(qualifier = LIST_RATINGS_DTO_MAPPER.qualifier) }

    private val ratingMapper by lazy { get<RatingMapper>() }

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(appModule)
    }

    @Test
    fun testMapper() {

        listRatingsId.zip(listRatingsDTO) { playerId, ratingDTO ->

            val newRatingDTO = ratingMapper.map(playerId = playerId)

            assertEquals(expected = newRatingDTO, actual = ratingDTO)
        }
    }
}