package pro.liga.di.test

import java.time.LocalDateTime
import org.koin.dsl.module
import pro.liga.data.tournament.TournamentDTO
import pro.liga.data.tournament.options.DayOfTheWeek.MONDAY
import pro.liga.data.tournament.options.DayOfTheWeek.SATURDAY
import pro.liga.data.tournament.options.DayOfTheWeek.WEDNESDAY
import pro.liga.data.tournament.options.League.LEAGUE_250_300
import pro.liga.data.tournament.options.League.LEAGUE_550_600
import pro.liga.data.tournament.options.League.LEAGUE_900_1000
import pro.liga.data.tournament.options.Room.A15
import pro.liga.data.tournament.options.Room.A4
import pro.liga.data.tournament.options.Room.A6
import pro.liga.data.tournament.options.Type.FINISHED
import pro.liga.data.tournament.options.Type.NOT_STARTED
import pro.liga.di.Qualifiers
import pro.liga.di.Qualifiers.FIRST
import pro.liga.di.Qualifiers.LIST_TOURNAMENT_PLAYERS_DTO_FIRST
import pro.liga.di.Qualifiers.LIST_TOURNAMENT_PLAYERS_DTO_SECOND
import pro.liga.di.Qualifiers.LIST_TOURNAMENT_PLAYERS_DTO_THIRD
import pro.liga.di.Qualifiers.SECOND
import pro.liga.di.Qualifiers.THIRD

val testTournamentModule = module {

    single(qualifier = FIRST.qualifier) {
        TournamentDTO(
            id = 1,
            league = LEAGUE_250_300,
            room = A15,
            type = FINISHED,
            dateTime = LocalDateTime.now(),
            dayOfTheWeek = MONDAY,
            players = get(qualifier = LIST_TOURNAMENT_PLAYERS_DTO_FIRST.qualifier)
        )
    }

    single(qualifier = SECOND.qualifier) {
        TournamentDTO(
            id = 2,
            league = LEAGUE_550_600,
            room = A4,
            type = FINISHED,
            dateTime = LocalDateTime.now(),
            dayOfTheWeek = WEDNESDAY,
            players = get(qualifier = LIST_TOURNAMENT_PLAYERS_DTO_SECOND.qualifier)
        )
    }

    single(qualifier = THIRD.qualifier) {
        TournamentDTO(
            id = 3,
            league = LEAGUE_900_1000,
            room = A6,
            type = NOT_STARTED,
            dateTime = LocalDateTime.now(),
            dayOfTheWeek = SATURDAY,
            players = get(qualifier = LIST_TOURNAMENT_PLAYERS_DTO_THIRD.qualifier)
        )
    }

    single(qualifier = Qualifiers.LIST_TOURNAMENTS_DTO.qualifier) {
        listOf<TournamentDTO>(
            get(FIRST.qualifier),
            get(SECOND.qualifier),
            get(THIRD.qualifier)
        )
    }
}
