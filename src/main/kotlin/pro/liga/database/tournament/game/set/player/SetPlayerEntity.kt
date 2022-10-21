package pro.liga.database.tournament.game.set.player

import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pro.liga.data.tournament.game.set.player.SetPlayerDTO
import pro.liga.database.MyLongEntity
import pro.liga.database.player.PlayerEntity
import pro.liga.database.tournament.game.set.SetEntity

class SetPlayerEntity(id: EntityID<Long>) : MyLongEntity<SetPlayerDTO>(id = id, clazz = SetPlayerDTO::class),
    KoinComponent {

    private val setPlayerTransaction = get<SetPlayerTransaction>()

    var win: Boolean by SetPlayerModel.win
    var pointsCount: Short by SetPlayerModel.pointsCount
    var winSets: Short by SetPlayerModel.winSets

    var player: PlayerEntity by PlayerEntity referencedOn SetPlayerModel.player
    var set: SetEntity by SetEntity referencedOn SetPlayerModel.set

    val playerEntity: PlayerEntity
        get() = setPlayerTransaction.getPlayerEntity(entity = this)

    val setEntity: SetEntity
        get() = setPlayerTransaction.getSetEntity(entity = this)

    val playerId: Int
        get() = playerEntity.id.value

    val setId: Long
        get() = setEntity.id.value

    companion object : LongEntityClass<SetPlayerEntity>(SetPlayerModel)
}