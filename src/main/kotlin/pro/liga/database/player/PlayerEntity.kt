package pro.liga.database.player

import java.time.format.DateTimeFormatter
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PlayerEntity(id: EntityID<Int>) : IntEntity(id)  {

    var firstName by PlayerModel.firstName
    var lastName by PlayerModel.lastName
    var patronymic by PlayerModel.patronymic
    var date by PlayerModel.date
    var rank by PlayerModel.rank

    override fun toString(): String {
        return "id = $id, " +
                "$lastName $firstName ${patronymic.orEmpty()}, " +
                "date = ${date.format(formatter)}, " +
                "rank = $rank"
    }

    companion object : IntEntityClass<PlayerEntity>(PlayerModel) {
        private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    }
}