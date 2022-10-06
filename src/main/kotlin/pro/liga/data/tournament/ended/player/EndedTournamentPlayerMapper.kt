package com.example.data.tournament.player

import pro.liga.data.tournament.ended.player.EndedTournamentPlayer

class EndedTournamentPlayerMapper {
    fun map(endedTournamentPlayer: EndedTournamentPlayer): EndedTournamentPlayerDTO {
        val place = endedTournamentPlayer.index + 1
        val playerId = endedTournamentPlayer.link.removePrefix(PREFIX).toInt()

        return EndedTournamentPlayerDTO(
            place = place,
            playerId = playerId,
            tournamentId = endedTournamentPlayer.tournamentId
        )
    }

    companion object {
        private const val PREFIX = "players/"
    }
}