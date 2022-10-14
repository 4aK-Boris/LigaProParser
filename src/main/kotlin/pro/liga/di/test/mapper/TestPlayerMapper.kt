package pro.liga.di.test.mapper

import java.time.LocalDate
import org.koin.dsl.module
import pro.liga.data.player.Player
import pro.liga.data.player.PlayerDTO
import pro.liga.data.player.rating.RatingDTO
import pro.liga.di.Qualifiers.*

val testPlayerMapper = module {
    single(qualifier = LIST_PLAYERS_ID.qualifier) {
        listOf(6618, 6619, 357, 817, 634)
    }

    single(qualifier = LIST_PLAYERS.qualifier) {
        listOf(
            Player(
                id = 6618,
                name = "Смирнов Михаил Борисович",
                info = listOf("1377", "ЛигаПРО", "267", "14.10.2022")
            ),
            null,
            Player(
                id = 357,
                name = "Тихонов Артем Андреевич",
                info = listOf("102", "ЛигаПРО", "1265", "26.03.2020")
            ),
            null,
            null
        )
    }

    single(qualifier = LIST_PLAYERS_MAPPER.qualifier) {
        listOf(
            Player(
                id = 6618,
                name = "Смирнов Михаил Борисович",
                info = listOf("1377", "ЛигаПРО", "267", "14.10.2022")
            ),
            Player(
                id = 357,
                name = "Тихонов Артем Андреевич",
                info = listOf("102", "ЛигаПРО", "1265", "26.03.2020")
            ),
            Player(
                id = 1,
                name = "Лосев Дмитрий",
                info = listOf("1", "ЛигаПРО", "10000", "14.10.2022")
            )
        )
    }

    single(qualifier = LIST_PLAYERS_DTO_MAPPER.qualifier) {
        listOf(
            PlayerDTO(
                id = 6618,
                firstName = "Михаил",
                lastName = "Смирнов",
                patronymic = "Борисович",
                rank = 1377,
                date = LocalDate.of(2022, 10, 14),
                rating = 267
            ),
            null,
            PlayerDTO(
                id = 1,
                firstName = "Дмитрий",
                lastName = "Лосев",
                patronymic = null,
                rank = 1,
                rating = 10000,
                date = LocalDate.now()
            )
        )
    }
}