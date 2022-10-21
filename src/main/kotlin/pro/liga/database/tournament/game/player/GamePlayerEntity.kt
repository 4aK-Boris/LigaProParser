package pro.liga.database.tournament.game.player

import pro.liga.data.tournament.game.player.GamePlayerDTO
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pro.liga.database.MyIntEntity
import pro.liga.database.player.PlayerEntity
import pro.liga.database.tournament.game.GameEntity
import pro.liga.database.tournament.game.type.FinalStateType
import pro.liga.database.tournament.game.type.SemiFinalStateType

class GamePlayerEntity(id: EntityID<Int>) : MyIntEntity<GamePlayerDTO>(id = id, clazz = GamePlayerDTO::class),
    KoinComponent {

    private val gamePlayerTransaction = get<GamePlayerTransaction>()

    var number: Short? by GamePlayerModel.number
    var winSets: Short? by GamePlayerModel.winSets
    var winPoints: Short? by GamePlayerModel.winPoints
    var submission: Float? by GamePlayerModel.submission

    val finalStateType: FinalStateType? by GamePlayerModel.finalStateType
    val semiFinalStateType: SemiFinalStateType? by GamePlayerModel.semiFinalStateType

    var game: GameEntity by GameEntity referencedOn GamePlayerModel.game
    var player by PlayerEntity referencedOn GamePlayerModel.player

    val gameEntity: GameEntity
        get() = gamePlayerTransaction.getGameEntity(entity = this)

    val playerEntity: PlayerEntity
        get() = gamePlayerTransaction.getPlayerEntity(entity = this)

    val gameId: Int
        get() = gameEntity.id.value

    val playerId: Int
        get() = playerEntity.id.value

    companion object : IntEntityClass<GamePlayerEntity>(GamePlayerModel)
}

