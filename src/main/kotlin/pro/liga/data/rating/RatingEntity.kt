package pro.liga.data.rating

import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class RatingEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<RatingEntity>(RatingModel)
    var playerId by RatingModel.playerId
    var rating by RatingModel.rating
    var date by RatingModel.date
}