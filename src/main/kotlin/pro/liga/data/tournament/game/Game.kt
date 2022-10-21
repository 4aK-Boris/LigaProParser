package pro.liga.data.tournament.game

import java.time.LocalDateTime

data class Game(
    val link: String,
    val tournamentId: Int,
    val dateTime: LocalDateTime,
    val players: Pair<Int, Int>,
    val listSets: List<List<Int>>?
)