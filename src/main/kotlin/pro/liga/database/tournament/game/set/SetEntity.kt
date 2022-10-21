package pro.liga.database.tournament.game.set

import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pro.liga.data.tournament.game.set.SetDTO
import pro.liga.database.MyLongEntity
import pro.liga.database.tournament.game.GameEntity

class SetEntity(id: EntityID<Long>) : MyLongEntity<SetDTO>(id = id, clazz = SetDTO::class), KoinComponent {

    private val setTransaction = get<SetTransaction>()

    var order: Short by SetModel.order
    var countScore: Short by SetModel.countScore
    var listScore: String by SetModel.listScore

    var game: GameEntity by GameEntity referencedOn SetModel.game

    val gameEntity: GameEntity
        get() = setTransaction.getGameEntity(entity = this)

    val gameId: Int
        get() = gameEntity.id.value

    companion object : LongEntityClass<SetEntity>(SetModel)
}