package pro.liga.database.tournament.game

import java.time.LocalDateTime
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pro.liga.data.tournament.game.GameDTO
import pro.liga.database.MyIntEntity
import pro.liga.database.tournament.TournamentEntity
import pro.liga.database.tournament.game.type.FinalStateType
import pro.liga.database.tournament.game.type.MatchType
import pro.liga.database.tournament.game.type.SemiFinalStateType

class GameEntity(id: EntityID<Int>) : MyIntEntity<GameDTO>(id = id, clazz = GameDTO::class), KoinComponent {

    private val gameTransaction = get<GameTransaction>()

    var dateTime: LocalDateTime by GameModel.dateTime
    var ended: Boolean by GameModel.ended
    var matchType: MatchType by GameModel.matchType

    var tournament: TournamentEntity by TournamentEntity referencedOn GameModel.tournament

    val tournamentEntity: TournamentEntity
        get() = gameTransaction.getTournamentEntity(entity = this)

    val tournamentId: Int
        get() = tournamentEntity.id.value

    companion object : IntEntityClass<GameEntity>(GameModel)
}