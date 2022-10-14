package pro.liga.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import pro.liga.database.player.PlayerTransaction
import pro.liga.database.player.rating.RatingTransaction
import pro.liga.database.player.tournament.TournamentPlayerTransaction
import pro.liga.database.tournament.TournamentTransaction

val transactionModule = module {

    factoryOf(::RatingTransaction)

    factoryOf(::PlayerTransaction)

    factoryOf(::TournamentTransaction)

    factoryOf(::TournamentPlayerTransaction)
}