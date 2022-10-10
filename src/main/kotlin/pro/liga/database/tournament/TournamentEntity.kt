package pro.liga.database.tournament

import java.time.LocalDateTime
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import pro.liga.data.tournament.TournamentDTO
import pro.liga.data.tournament.options.DayOfTheWeek
import pro.liga.data.tournament.options.League
import pro.liga.data.tournament.options.Room
import pro.liga.data.tournament.options.Type
import pro.liga.database.MainEntity
import pro.liga.database.hasEquals
import pro.liga.database.player.tournament.TournamentPlayerEntity
import pro.liga.database.player.tournament.TournamentPlayerModel

class TournamentEntity(id: EntityID<Int>) : IntEntity(id), MainEntity {

    var league: League by TournamentModel.league
    var room: Room by TournamentModel.room
    var type: Type by TournamentModel.type
    var dateTime: LocalDateTime by TournamentModel.dateTime
    var dayOfTheWeek: DayOfTheWeek by TournamentModel.dayOfTheWeek

    val players by TournamentPlayerEntity referrersOn TournamentPlayerModel.tournamentId

    override fun toString(): String {
        return objectToString()
    }

    override fun equals(other: Any?): Boolean {
        return hasEquals<TournamentDTO>(other = other)
    }

    override fun hashCode(): Int {
        return getHashCode()
    }

    companion object : IntEntityClass<TournamentEntity>(TournamentModel)
}