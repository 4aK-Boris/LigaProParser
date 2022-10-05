package pro.liga.data.tournament

data class Tournament(
    val id: Int,
    val title: String,
    val type: String,
    val date: String,
    val time: String,
    val dayOfTheWeek: String,
    val players: List<String>,
    val games: List<Pair<String, String>>
)
