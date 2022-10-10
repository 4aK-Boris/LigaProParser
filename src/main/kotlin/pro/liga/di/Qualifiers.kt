package pro.liga.di

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named

enum class Qualifiers {
    FIRST,
    SECOND,
    THIRD,
    LIST_PLAYERS_DTO,
    LIST_RATINGS_DTO,
    LIST_PLAYER_DTO_WITH_RATING_DTO,
    LIST_UPDATE_PLAYERS_DTO,
    LIST_TOURNAMENTS_DTO,
    LIST_TOURNAMENTS_PLAYERS_DTO,
    LIST_TOURNAMENT_PLAYERS_DTO_FIRST,
    LIST_TOURNAMENT_PLAYERS_DTO_SECOND,
    LIST_TOURNAMENT_PLAYERS_DTO_THIRD,
    LIST_RATINGS;

    val qualifier: Qualifier = named(enum = this)
}