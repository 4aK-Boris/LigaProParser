package pro.liga.database.tournament.ended

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import pro.liga.data.tournament.DayOfTheWeek
import pro.liga.data.tournament.League
import pro.liga.data.tournament.TournamentType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import pro.liga.database.player.tournament.EndedTournamentPlayerModel

class EndedTournamentEntity(id: EntityID<Int>) : IntEntity(id) {

    var league: League by EndedTournamentModel.league
    var tournamentType: TournamentType by EndedTournamentModel.tournamentType
    var dateTime: LocalDateTime by EndedTournamentModel.dateTime
    var dayOfTheWeek: DayOfTheWeek by EndedTournamentModel.dayOfTheWeek

    val players by EndedTournamentEntity referrersOn EndedTournamentPlayerModel.tournamentId

    override fun toString(): String {
        return "id = ${id.value}, " +
                "league = ${league.leagueName}, " +
                "tournamentType = ${tournamentType.title}, " +
                "dateTime = ${dateTime.format(formatter)}, " +
                "dayOfTheWeek = ${dayOfTheWeek.title}"
    }

    companion object : IntEntityClass<EndedTournamentEntity>(EndedTournamentModel) {
        private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy")
    }
}