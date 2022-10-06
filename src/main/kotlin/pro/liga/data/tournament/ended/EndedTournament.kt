package pro.liga.data.tournament.ended

data class EndedTournament(
    val id: Int,
    val title: String,
    val type: String,
    val date: String,
    val time: String,
    val dayOfTheWeek: String,
    val players: List<String>,
    val games: List<Pair<String, String>>
)
