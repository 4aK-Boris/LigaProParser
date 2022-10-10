package pro.liga.di

import org.koin.dsl.module
import pro.liga.database.player.PlayerTransaction
import pro.liga.database.player.rating.RatingTransaction
import pro.liga.database.player.tournament.TournamentPlayerTransaction
import pro.liga.database.tournament.TournamentTransaction

val transactionModule = module {

    factory {
        RatingTransaction()
    }

    factory {
        PlayerTransaction()
    }

    factory {
        TournamentTransaction()
    }

    factory {
        TournamentPlayerTransaction()
    }
}