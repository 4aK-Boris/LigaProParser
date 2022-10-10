package pro.liga.di.test

import java.time.LocalDate
import org.koin.dsl.module
import pro.liga.data.player.PlayerDTO
import pro.liga.di.Qualifiers.FIRST
import pro.liga.di.Qualifiers.LIST_PLAYERS_DTO
import pro.liga.di.Qualifiers.LIST_UPDATE_PLAYERS_DTO
import pro.liga.di.Qualifiers.SECOND
import pro.liga.di.Qualifiers.THIRD

val testPlayerModule = module {

    single(qualifier = FIRST.qualifier) {
        PlayerDTO(
            id = 1,
            firstName = "Дмитрий",
            lastName = "Лосев",
            patronymic = "Алексеевич",
            rank = 100,
            date = LocalDate.now(),
            ratingDTO = get(qualifier = FIRST.qualifier)
        )
    }

    single(qualifier = SECOND.qualifier) {
        PlayerDTO(
            id = 2,
            firstName = "Никита",
            lastName = "Архипов",
            patronymic = "Антонович",
            rank = 150,
            date = LocalDate.now(),
            ratingDTO = get(qualifier = SECOND.qualifier)
        )
    }

    single(qualifier = THIRD.qualifier) {
        PlayerDTO(
            id = 3,
            firstName = "Александр",
            lastName = "Федоткин",
            patronymic = "Александрович",
            rank = 200,
            date = LocalDate.now(),
            ratingDTO = get(qualifier = THIRD.qualifier)
        )
    }

    single(qualifier = LIST_PLAYERS_DTO.qualifier) {
        listOf<PlayerDTO>(
            get(qualifier = FIRST.qualifier),
            get(qualifier = SECOND.qualifier),
            get(qualifier = THIRD.qualifier),
        )
    }

    single(qualifier = LIST_UPDATE_PLAYERS_DTO.qualifier) {
        val listUpdatePlayerDTO = listOf<PlayerDTO>(
            get(qualifier = SECOND.qualifier),
            get(qualifier = THIRD.qualifier),
            get(qualifier = FIRST.qualifier),
        )
        val listPlayerDTO = get<List<PlayerDTO>>(qualifier = LIST_PLAYERS_DTO.qualifier)

        listPlayerDTO.zip(listUpdatePlayerDTO) { old, new ->
            old to new.copy(id = old.id)
        }
    }
}