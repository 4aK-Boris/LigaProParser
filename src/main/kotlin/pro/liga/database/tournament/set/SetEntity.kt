package pro.liga.database.tournament.set

import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import pro.liga.data.tournament.set.SetDTO
import pro.liga.database.MyLongEntity

class SetEntity(id: EntityID<Long>) : MyLongEntity<SetDTO>(id = id, clazz = SetDTO::class) {

    var order: Int by SetModel.order
    var countScore: Int by SetModel.countScore
    var gameId: Int by SetModel.gameId
    var listScore: String by SetModel.listScore

    companion object : LongEntityClass<SetEntity>(SetModel)
}