package pro.liga.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import pro.liga.data.player.PlayerMapper
import pro.liga.data.player.rating.RatingMapper

val mapperModule = module {

    factory {
        RatingMapper(logger = logger)
    }

    factory {
        PlayerMapper(logger = logger)
    }
}