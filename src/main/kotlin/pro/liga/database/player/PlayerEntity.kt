package pro.liga.database.player

import java.time.LocalDate
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import pro.liga.data.player.PlayerDTO
import pro.liga.database.MainEntity
import pro.liga.database.hasEquals
import pro.liga.database.player.rating.RatingEntity
import pro.liga.database.player.rating.RatingModel

class PlayerEntity(id: EntityID<Int>) : IntEntity(id), MainEntity {

    var firstName: String by PlayerModel.firstName
    var lastName: String by PlayerModel.lastName
    var patronymic: String? by PlayerModel.patronymic
    var date: LocalDate by PlayerModel.date
    var rank: Short by PlayerModel.rank

    val ratings by RatingEntity referrersOn RatingModel.player

    override fun toString(): String {
        return objectToString()
    }

    override fun equals(other: Any?): Boolean {
        return hasEquals<PlayerDTO>(other = other)
    }

    override fun hashCode(): Int {
        return getHashCode()
    }

    companion object : IntEntityClass<PlayerEntity>(PlayerModel)
}