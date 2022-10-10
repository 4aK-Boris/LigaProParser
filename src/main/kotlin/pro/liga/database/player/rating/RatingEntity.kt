package pro.liga.database.player.rating

import java.time.LocalDate
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pro.liga.data.player.rating.RatingDTO
import pro.liga.database.MainEntity
import pro.liga.database.hasEquals
import pro.liga.database.player.PlayerEntity
import kotlin.reflect.full.declaredMemberProperties

class RatingEntity(id: EntityID<Long>) : LongEntity(id), KoinComponent, MainEntity {

    private val ratingTransaction = get<RatingTransaction>()

    var rating: Int by RatingModel.rating
    var date: LocalDate by RatingModel.date

    var player: PlayerEntity by PlayerEntity referencedOn RatingModel.player

    val playerId: Int
        get() = ratingTransaction.getPlayerId(entity = this)

    override fun toString(): String {
        return objectToString()
    }

    override fun equals(other: Any?): Boolean {
        return hasEquals<RatingDTO>(other = other)
    }

    override fun hashCode(): Int {
        return getHashCode()
    }

    companion object : LongEntityClass<RatingEntity>(RatingModel)
}