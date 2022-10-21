package pro.liga.database.tournament.set.player

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import pro.liga.database.player.PlayerModel
import pro.liga.database.tournament.set.SetModel

object SetPlayerModel: LongIdTable(name = "sets_players") {

    val win = bool("win")
    val pointsCount = short("points_count")
    val winSets = short("win_sets")

    val player = reference(
        name = "player_id",
        foreign = PlayerModel,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.NO_ACTION,
        fkName = "player"
    )

    val set = reference(
        name = "set_id",
        foreign = SetModel,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.NO_ACTION,
        fkName = "set"
    )
}