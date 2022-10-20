package pro.liga.data.game

import org.jsoup.Jsoup

class GameMapper {

    fun map(id: Int) {
        val document = Jsoup.connect("https://tt.sport-liga.pro/games/203561").cookieStore().cookies
        println(document)
    }
    fun map(game: Game): Unit {//GameDTO {
        val id = game.link.removePrefix(PREFIX).toInt()
        val countSets = game.listSets?.size
        //val setsDTO = game.listSets?.let { getSetsDTO(sets = it, gameId = id, players = game.players) }
        //val countPoints = setsDTO?.sumOf { it.countScore }
        //val firstScore = setsDTO?.count { it.players.first.playerId == game.players.first }
        //val secondScore = setsDTO?.count { it.winningPlayer == game.players.second }
//        val winningPlayer = when {
//            firstScore == null || secondScore == null -> null
//            firstScore > secondScore -> game.players.first
//            else -> game.players.second
//        }
//        val losingPlayer = when {
//            firstScore == null || secondScore == null -> null
//            firstScore < secondScore -> game.players.first
//            else -> game.players.second
//        }
        //val scorePointsFirst = setsDTO?.sumOf { it.score.first }
        //val scorePointsSecond = setsDTO?.sumOf { it.score.second }
        val playerSubmissionFirst = game.listSets?.let { getPlayerSubmission(listSets = it, player = 1) }
        val playerSubmissionSecond = game.listSets?.let { getPlayerSubmission(listSets = it, player = 2) }
        val playerSubmissionFirstOther = playerSubmissionSecond?.let { 1 - it }
        val playerSubmissionSecondOther = playerSubmissionFirst?.let { 1 - it }

//        return GameDTO(
//            id = id,
//            //type = true,
//            tournamentId = game.tournamentId,
//            //players = game.players,
//            countSets = countSets,
//            //countPoints = countPoints,
////            score = firstScore to secondScore,
////            winningPlayer = winningPlayer,
////            losingPlayer = losingPlayer,
////            scorePoints = scorePointsFirst to scorePointsSecond,
////            sets = setsDTO,
////            playerSubmission = playerSubmissionFirst to playerSubmissionSecond,
////            playerSubmissionOther = playerSubmissionFirstOther to playerSubmissionSecondOther,
//            dateTime = game.dateTime
//        )
    }

//    private fun getSetsDTO(sets: List<List<Int>>, gameId: Int, players: Pair<Int, Int>): List<SetDTO> {
//        return sets.mapIndexed { index, listScore ->
////            val set = Set(
////                gameId = gameId,
////                order = index + 1,
////                players = players,
////                listScore = listScore
////            )
//            setMapper.map(set)
//        }
//    }

    private fun getPlayerSubmission(listSets: List<List<Int>>, player: Int): Float {
        var counter = 0 to 0
        for (i in listSets.indices) {
            counter += getSubmission(i, listSets[i], player)
        }
        return counter.first.toFloat() / counter.second.toFloat()
    }

    private fun getSubmission(index: Int, listScore: List<Int>, player: Int): Pair<Int, Int> {
        var (win, all) = 0 to 0
        var flag = index % 2 == (player - 1)
        for (i in listScore.indices) {
            if (flag) {
                all++
                if (player == listScore[i]) win++
            }
            if (i >= 20 || i % 2 == 1) flag = !flag
        }
        return win to all
    }

    companion object {
        private const val PREFIX = "games/"

        //private val setMapper = SetMapper()

        private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> {
            return this.first + other.first to this.second + other.second
        }
    }
}
