package pro.liga.database.entity

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import pro.liga.data.tournament.DayOfTheWeek
import pro.liga.data.tournament.League
import pro.liga.data.tournament.TournamentType
import pro.liga.data.tournament.ended.EndedTournamentModel
import java.time.LocalDateTime

class EndedTournament(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<EndedTournament>(EndedTournamentModel)
    var league: League by EndedTournamentModel.league
    var type: TournamentType by EndedTournamentModel.type
    var dateTime: LocalDateTime by EndedTournamentModel.dateTime
    var dayOfTheWeek: DayOfTheWeek by EndedTournamentModel.dayOfTheWeek
}