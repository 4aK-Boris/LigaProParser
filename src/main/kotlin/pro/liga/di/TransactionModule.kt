package pro.liga.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import pro.liga.database.player.PlayerTransaction
import pro.liga.database.player.rating.RatingTransaction
import pro.liga.database.player.tournament.TournamentPlayerTransaction
import pro.liga.database.tournament.TournamentTransaction
import pro.liga.database.tournament.game.GameTransaction
import pro.liga.database.tournament.game.player.GamePlayerTransaction
import pro.liga.database.tournament.game.set.SetTransaction
import pro.liga.database.tournament.game.set.player.SetPlayerTransaction

val transactionModule = module {

    factoryOf(::RatingTransaction)

    factoryOf(::PlayerTransaction)

    factoryOf(::TournamentTransaction)

    factoryOf(::TournamentPlayerTransaction)

    factoryOf(::GameTransaction)

    factoryOf(::GamePlayerTransaction)

    factoryOf(::SetTransaction)

    factoryOf(::SetPlayerTransaction)
}