package pro.liga.di

import org.koin.dsl.module
import pro.liga.data.player.rating.RatingMapper

val mapperModule = module {

    factory {
        RatingMapper()
    }
}