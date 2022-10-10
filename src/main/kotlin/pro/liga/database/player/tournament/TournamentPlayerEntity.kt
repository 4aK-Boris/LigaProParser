package pro.liga.database.player.tournament

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pro.liga.data.player.tournament.TournamentPlayerDTO
import pro.liga.database.MainEntity
import pro.liga.database.hasEquals
import pro.liga.database.player.PlayerEntity
import pro.liga.database.tournament.TournamentEntity

class TournamentPlayerEntity(id: EntityID<Int>) : IntEntity(id), KoinComponent, MainEntity {

    private val tournamentPlayerTransaction = get<TournamentPlayerTransaction>()

    var place: Int? by TournamentPlayerModel.place

    var player: PlayerEntity by PlayerEntity referencedOn TournamentPlayerModel.player
    var tournament: TournamentEntity by TournamentEntity referencedOn TournamentPlayerModel.tournamentId

    val playerId: Int
        get() = tournamentPlayerTransaction.getPlayerId(entity = this)

    val tournamentId: Int
        get() = tournamentPlayerTransaction.getTournamentId(entity = this)

    override fun toString(): String {
        return objectToString()
    }

    override fun equals(other: Any?): Boolean {
        return hasEquals<TournamentPlayerDTO>(other = other)
    }

    override fun hashCode(): Int {
        return getHashCode()
    }

    companion object : IntEntityClass<TournamentPlayerEntity>(TournamentPlayerModel)
}