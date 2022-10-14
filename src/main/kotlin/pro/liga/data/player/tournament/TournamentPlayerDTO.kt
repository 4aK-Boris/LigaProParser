package pro.liga.data.player.tournament

import pro.liga.data.DTO

data class TournamentPlayerDTO(
    val place: Int?,
    val playerId: Int,
    val tournamentId: Int
): DTO
