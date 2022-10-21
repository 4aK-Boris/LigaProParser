package pro.liga.database.tournament.set

import com.example.data.game.GameModel
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object SetModel: LongIdTable(name = "sets") {

    private const val MAX_LENGTH = 100

    val order = integer("order")
    val countScore = integer("count_score")
    val listScore = varchar("list_score", MAX_LENGTH)
    val gameId = integer("game_id").references(
        ref = GameModel.id,
        onUpdate = ReferenceOption.NO_ACTION,
        onDelete = ReferenceOption.CASCADE
    )
}