package pro.liga.di.test

import java.time.LocalDate
import org.koin.core.qualifier.named
import org.koin.dsl.module
import pro.liga.data.player.PlayerDTO

private const val ONE = "1"
private const val TWO = "2"
private const val THREE = "3"

fun testPlayerModule() = module {

    includes(testRatingModule())

    single(qualifier = named(ONE)) {
        PlayerDTO(
            id = 1,
            firstName = "Дмитрий",
            lastName = "Лосев",
            patronymic = "Алексеевич",
            rank = 100,
            date = LocalDate.now(),
            ratingDTO = get(qualifier = named(ONE))
        )
    }

    single(qualifier = named(TWO)) {
        PlayerDTO(
            id = 2,
            firstName = "Никита",
            lastName = "Архипов",
            patronymic = "Антонович",
            rank = 150,
            date = LocalDate.now(),
            ratingDTO = get(qualifier = named(TWO))
        )
    }

    single(qualifier = named(THREE)) {
        PlayerDTO(
            id = 3,
            firstName = "Александр",
            lastName = "Федоткин",
            patronymic = "Александрович",
            rank = 200,
            date = LocalDate.now(),
            ratingDTO = get(qualifier = named(THREE))
        )
    }
}