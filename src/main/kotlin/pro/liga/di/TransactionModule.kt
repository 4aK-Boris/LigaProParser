package pro.liga.di

import org.koin.dsl.module
import pro.liga.database.player.PlayerTransaction
import pro.liga.database.player.rating.RatingTransaction
import pro.liga.database.player.tournament.EndedTournamentPlayerTransaction
import pro.liga.database.tournament.ended.EndedTournamentTransaction

val transactionModule = module {

    factory {
        RatingTransaction()
    }

    factory {
        PlayerTransaction()
    }

    factory {
        EndedTournamentTransaction()
    }

    factory {
        EndedTournamentPlayerTransaction()
    }
}