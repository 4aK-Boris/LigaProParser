package pro.liga.database.player.rating

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import pro.liga.database.player.PlayerEntity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RatingEntity(id: EntityID<Long>) : LongEntity(id) {

    var rating: Int by RatingModel.rating
    var date: LocalDate by RatingModel.date

    var player: PlayerEntity by PlayerEntity referencedOn RatingModel.player

    override fun toString(): String {
        return "id = $id, " +
                "rating = $rating, " +
                "date = ${date.format(formatter)}, " +
                "player = $player"
    }

    companion object : LongEntityClass<RatingEntity>(RatingModel) {
        private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    }

}