package pro.liga.database.player.tournament

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import pro.liga.database.player.PlayerEntity
import pro.liga.database.tournament.ended.EndedTournamentEntity
import pro.liga.database.tournament.ended.EndedTournamentModel

class EndedTournamentPlayerEntity(id: EntityID<Int>) : IntEntity(id) {

    var place: Int by EndedTournamentPlayerModel.place

    var player: PlayerEntity by PlayerEntity referencedOn EndedTournamentPlayerModel.player
    var tournament: EndedTournamentEntity by EndedTournamentEntity referencedOn EndedTournamentPlayerModel.tournamentId

    override fun toString(): String {
        return "id = ${id.value}, " +
                "place = $place" +
                "player = $player, " +
                "tournament = $tournament"
    }

    companion object : IntEntityClass<EndedTournamentPlayerEntity>(EndedTournamentPlayerModel)
}