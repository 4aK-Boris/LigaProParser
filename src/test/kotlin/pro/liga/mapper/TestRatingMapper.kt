package pro.liga.mapper

import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import pro.liga.data.player.rating.Rating
import pro.liga.data.player.rating.RatingDTO
import pro.liga.data.player.rating.RatingMapper
import pro.liga.di.Qualifiers.LIST_RATINGS
import pro.liga.di.Qualifiers.LIST_RATINGS_DTO
import pro.liga.di.appModule
import kotlin.test.assertEquals

class TestRatingMapper: KoinTest {

    private val listRatings by lazy { get<List<Rating>>(qualifier = LIST_RATINGS.qualifier) }
    private val listRatingsDTO by lazy { get<List<MutableList<RatingDTO>>>(qualifier = LIST_RATINGS_DTO.qualifier) }

    private val ratingMapper by lazy { get<RatingMapper>() }

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(appModule)
    }

    @Test
    fun testMapper() {

        listRatings.zip(listRatingsDTO) { rating, ratingDTO ->

            val newRatingDTO = ratingMapper.map(rating = rating)

            assertEquals(expected = newRatingDTO, actual = ratingDTO.first())
        }
    }
}