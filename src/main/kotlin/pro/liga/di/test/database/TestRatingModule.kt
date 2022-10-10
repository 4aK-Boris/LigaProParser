package pro.liga.di.test.database

import java.time.LocalDate
import org.koin.dsl.module
import pro.liga.data.player.PlayerDTO
import pro.liga.data.player.rating.RatingDTO
import pro.liga.di.Qualifiers.FIRST
import pro.liga.di.Qualifiers.LIST_PLAYERS_DTO
import pro.liga.di.Qualifiers.LIST_PLAYER_DTO_WITH_RATING_DTO
import pro.liga.di.Qualifiers.LIST_RATINGS_DTO
import pro.liga.di.Qualifiers.SECOND
import pro.liga.di.Qualifiers.THIRD

val testRatingModule = module {

    single(qualifier = FIRST.qualifier) {
        RatingDTO(playerId = 1, rating = 500, date = LocalDate.now())
    }

    single(qualifier = SECOND.qualifier) {
        RatingDTO(playerId = 2, rating = 750, date = LocalDate.now())
    }

    single(qualifier = THIRD.qualifier) {
        RatingDTO(playerId = 3, rating = 1000, date = LocalDate.now())
    }

    single(qualifier = LIST_RATINGS_DTO.qualifier) {
        val playersRatingList: List<MutableList<RatingDTO>> = listOf(
            mutableListOf(get(qualifier = FIRST.qualifier)),
            mutableListOf(get(qualifier = SECOND.qualifier)),
            mutableListOf(get(qualifier = THIRD.qualifier))
        )

        playersRatingList.map { list ->
            repeat(10) {
                list.add(copyRatingDTO(ratingDTO = list[it]))
            }
            list
        }
    }

    single(qualifier = LIST_PLAYER_DTO_WITH_RATING_DTO.qualifier) {
        val listPlayerDTO = get<List<PlayerDTO>>(qualifier = LIST_PLAYERS_DTO.qualifier)
        val listRatingsDTO = get<List<List<RatingDTO>>>(qualifier = LIST_RATINGS_DTO.qualifier)
        listPlayerDTO zip listRatingsDTO
    }
}

private fun copyRatingDTO(ratingDTO: RatingDTO) =
    ratingDTO.copy(rating = ratingDTO.rating + 100, date = ratingDTO.date.plusMonths(1L))