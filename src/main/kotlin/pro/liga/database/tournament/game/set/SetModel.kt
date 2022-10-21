package pro.liga.database.tournament.game.set

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import pro.liga.database.tournament.game.GameModel

object SetModel: LongIdTable(name = "sets") {

    private const val MAX_LENGTH = 100

    val order: Column<Short> = short("order")
    val countScore: Column<Short> = short("count_score")
    val listScore: Column<String> = varchar("list_score", MAX_LENGTH)

    val game: Column<EntityID<Int>> = reference(
        name = "game_id",
        foreign = GameModel,
        onUpdate = ReferenceOption.NO_ACTION,
        onDelete = ReferenceOption.CASCADE,
        fkName = "game"
    )
}