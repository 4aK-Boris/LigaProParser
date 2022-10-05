package pro.liga.data.rating

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import pro.liga.data.player.PlayerModel

object RatingModel : Table("rating") {
    private val id = long("id").autoIncrement()
    private val idPlayer = integer("player_id") references PlayerModel.id
    private val rating = integer("rating")
    private val date = date("date")

    override val primaryKey = PrimaryKey(id, name = "pk_rating")

    suspend fun insert(ratingDTO: RatingDTO) {
        newSuspendedTransaction {
            insert {
                it[idPlayer] = ratingDTO.idPlayer
                it[rating] = ratingDTO.rating
                it[date] = ratingDTO.date
            }
        }
    }
}
