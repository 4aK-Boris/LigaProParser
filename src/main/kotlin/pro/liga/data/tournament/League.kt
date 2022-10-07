package pro.liga.data.tournament

enum class League(val leftBorder: Int, val rightBorder: Int) {
    LEAGUE_250_300(leftBorder = 250, rightBorder = 300) ,
    LEAGUE_300_350(leftBorder = 300, rightBorder = 350),
    LEAGUE_350_400(leftBorder = 350, rightBorder = 400),
    LEAGUE_400_450(leftBorder = 400, rightBorder = 450),
    LEAGUE_450_500(leftBorder = 450, rightBorder = 500),
    LEAGUE_500_550(leftBorder = 500, rightBorder = 550),
    LEAGUE_550_600(leftBorder = 550, rightBorder = 600),
    LEAGUE_600_700(leftBorder = 600, rightBorder = 700),
    LEAGUE_700_800(leftBorder = 700, rightBorder = 800),
    LEAGUE_800_900(leftBorder = 800, rightBorder = 900),
    LEAGUE_900_1000(leftBorder = 900, rightBorder = 1000);

    val leagueName: String
        get() = "Лига $leftBorder-$rightBorder"

    companion object {

        private val regex = Regex("\\d{3,4}")

        fun getLeague(type: String): League? {
            val borders = regex.findAll(input = type).map { it.value.toIntOrNull() }.toList()
            if (borders.size != 2) return null
            return League.values().firstOrNull { leagueType ->
                borders[0] == leagueType.leftBorder && borders[1] == leagueType.rightBorder
            }
        }
    }
}
