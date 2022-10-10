package pro.liga.di.test

import org.koin.dsl.module
import pro.liga.data.player.PlayerDTO
import pro.liga.data.player.tournament.TournamentPlayerDTO
import pro.liga.di.Qualifiers.LIST_PLAYERS_DTO
import pro.liga.di.Qualifiers.LIST_TOURNAMENTS_PLAYERS_DTO
import pro.liga.di.Qualifiers.LIST_TOURNAMENT_PLAYERS_DTO_FIRST
import pro.liga.di.Qualifiers.LIST_TOURNAMENT_PLAYERS_DTO_SECOND
import pro.liga.di.Qualifiers.LIST_TOURNAMENT_PLAYERS_DTO_THIRD

val testTournamentPlayerModule = module {

    single(qualifier = LIST_TOURNAMENT_PLAYERS_DTO_FIRST.qualifier) {
        val listPlayersDTO = get<List<PlayerDTO>>(qualifier = LIST_PLAYERS_DTO.qualifier).shuffled()
        listPlayersDTO.mapIndexed { place, playerDTO ->
            TournamentPlayerDTO(
                place = place,
                playerId = playerDTO.id,
                tournamentId = 1
            )
        }
    }

    single(qualifier = LIST_TOURNAMENT_PLAYERS_DTO_SECOND.qualifier) {
        val listTournamentPlayersDTO =
            get<List<TournamentPlayerDTO>>(qualifier = LIST_TOURNAMENT_PLAYERS_DTO_FIRST.qualifier)
        listTournamentPlayersDTO.shuffled().mapIndexed { place, tournamentPlayerDTO ->
            tournamentPlayerDTO.copy(place = place + 1, tournamentId = 2)
        }
    }

    single(qualifier = LIST_TOURNAMENT_PLAYERS_DTO_THIRD.qualifier) {
        val listTournamentPlayersDTO =
            get<List<TournamentPlayerDTO>>(qualifier = LIST_TOURNAMENT_PLAYERS_DTO_FIRST.qualifier)
        listTournamentPlayersDTO.shuffled().map {
            it.copy(place = null, tournamentId = 3)
        }
    }

    single(qualifier = LIST_TOURNAMENTS_PLAYERS_DTO.qualifier) {
        listOf<List<TournamentPlayerDTO>>(
            get(qualifier = LIST_TOURNAMENT_PLAYERS_DTO_FIRST.qualifier),
            get(qualifier = LIST_TOURNAMENT_PLAYERS_DTO_SECOND.qualifier),
            get(qualifier = LIST_TOURNAMENT_PLAYERS_DTO_THIRD.qualifier)
        ).flatten()
    }
}