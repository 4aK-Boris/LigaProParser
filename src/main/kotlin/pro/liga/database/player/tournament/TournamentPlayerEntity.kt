package pro.liga.database.player.tournament

import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pro.liga.data.player.tournament.TournamentPlayerDTO
import pro.liga.database.MyIntEntity
import pro.liga.database.player.PlayerEntity
import pro.liga.database.tournament.TournamentEntity

class TournamentPlayerEntity(id: EntityID<Int>) :
    MyIntEntity<TournamentPlayerDTO>(id = id, clazz = TournamentPlayerDTO::class), KoinComponent {

    private val tournamentPlayerTransaction = get<TournamentPlayerTransaction>()

    var place: Int? by TournamentPlayerModel.place

    var player: PlayerEntity by PlayerEntity referencedOn TournamentPlayerModel.player
    var tournament: TournamentEntity by TournamentEntity referencedOn TournamentPlayerModel.tournamentId

    val playerId: Int
        get() = tournamentPlayerTransaction.getPlayerId(entity = this)

    val tournamentId: Int
        get() = tournamentPlayerTransaction.getTournamentId(entity = this)

    companion object : IntEntityClass<TournamentPlayerEntity>(TournamentPlayerModel)
}