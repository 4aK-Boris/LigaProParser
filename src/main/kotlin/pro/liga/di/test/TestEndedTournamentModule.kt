package pro.liga.di.test

import java.time.LocalDateTime
import org.koin.core.qualifier.named
import org.koin.dsl.module
import pro.liga.data.tournament.DayOfTheWeek.MONDAY
import pro.liga.data.tournament.DayOfTheWeek.SUNDAY
import pro.liga.data.tournament.DayOfTheWeek.WEDNESDAY
import pro.liga.data.tournament.League.LEAGUE_250_300
import pro.liga.data.tournament.League.LEAGUE_500_550
import pro.liga.data.tournament.League.LEAGUE_800_900
import pro.liga.data.tournament.TournamentType.A15
import pro.liga.data.tournament.TournamentType.A4
import pro.liga.data.tournament.TournamentType.A6
import pro.liga.data.tournament.ended.EndedTournamentDTO

private const val TWO = "2"
private const val THREE = "3"

val testEndedTournamentModule = module {

    single {
        EndedTournamentDTO(
            id = 1,
            league = LEAGUE_800_900,
            tournamentType = A4,
            dateTime = LocalDateTime.now(),
            dayOfTheWeek = MONDAY
        )
    }

    single(qualifier = named(TWO)) {
        EndedTournamentDTO(
            id = 2,
            league = LEAGUE_500_550,
            tournamentType = A6,
            dateTime = LocalDateTime.now(),
            dayOfTheWeek = SUNDAY
        )
    }

    single(qualifier = named(THREE)) {
        EndedTournamentDTO(
            id = 3,
            league = LEAGUE_250_300,
            tournamentType = A15,
            dateTime = LocalDateTime.now(),
            dayOfTheWeek = WEDNESDAY
        )
    }
}