package pro.liga.database.player.rating

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.date
import pro.liga.database.player.PlayerModel

object RatingModel : LongIdTable("rating") {

   val player = reference(
       name = "player_id",
       foreign = PlayerModel,
       onDelete = ReferenceOption.CASCADE,
       onUpdate = ReferenceOption.NO_ACTION,
       fkName = "player"
   )
   val rating = short("rating")
   val date = date("date")
}
