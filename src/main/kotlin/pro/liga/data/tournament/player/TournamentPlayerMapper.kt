package com.example.data.tournament.player

class TournamentPlayerMapper {
    fun map(tournamentPlayer: TournamentPlayer): TournamentPlayerDTO {
        val place = tournamentPlayer.index + 1
        val playerId = tournamentPlayer.link.removePrefix(PREFIX).toInt()

        return TournamentPlayerDTO(
            place = place,
            playerId = playerId,
            tournamentId = tournamentPlayer.tournamentId
        )
    }

    companion object {
        private const val PREFIX = "players/"
    }
}