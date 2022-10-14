package pro.liga.database.player

import java.time.LocalDate
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import pro.liga.data.player.PlayerDTO
import pro.liga.database.MainEntity
import pro.liga.database.MyIntEntity
import pro.liga.database.player.rating.RatingEntity
import pro.liga.database.player.rating.RatingModel

class PlayerEntity(id: EntityID<Int>) : MyIntEntity<PlayerDTO>(id = id, clazz = PlayerDTO::class) {

    var firstName: String by PlayerModel.firstName
    var lastName: String by PlayerModel.lastName
    var patronymic: String? by PlayerModel.patronymic
    var date: LocalDate by PlayerModel.date
    var rank: Short by PlayerModel.rank

    val ratings by RatingEntity referrersOn RatingModel.player

    companion object : IntEntityClass<PlayerEntity>(PlayerModel)
}