package pro.liga.data.rating

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object RatingModel : LongIdTable("rating") {

    //private val id = long("id").autoIncrement()
   val playerId = integer("player_id") //references PlayerModel.id
   val rating = integer("rating")
   val date = date("date")

    //override val primaryKey = PrimaryKey(id, name = "pk_rating")

    fun insert(ratingDTO: RatingDTO) {
        transaction {
            insert {
                it[playerId] = ratingDTO.playerId
                it[rating] = ratingDTO.rating
                it[date] = ratingDTO.date
            }
        }
    }
}
