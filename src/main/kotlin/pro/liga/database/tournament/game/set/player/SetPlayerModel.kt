package pro.liga.database.tournament.game.set.player

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import pro.liga.database.player.PlayerModel
import pro.liga.database.tournament.game.set.SetModel

object SetPlayerModel: LongIdTable(name = "sets_players") {

    val win: Column<Boolean> = bool("win")
    val pointsCount: Column<Short> = short("points_count")
    val winSets: Column<Short> = short("win_sets")

    val player: Column<EntityID<Int>> = reference(
        name = "player_id",
        foreign = PlayerModel,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.NO_ACTION,
        fkName = "player"
    )

    val set: Column<EntityID<Long>> = reference(
        name = "set_id",
        foreign = SetModel,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.NO_ACTION,
        fkName = "set"
    )
}