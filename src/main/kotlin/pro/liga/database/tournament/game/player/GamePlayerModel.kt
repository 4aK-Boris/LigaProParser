package pro.liga.database.tournament.game.player

import org.jetbrains.exposed.dao.id.IntIdTable

object GamePlayerModel: IntIdTable(name = "games_players") {

    val winSets = short("win_sets")
    val winPoints = short("win_points")


}