package pro.liga.database.tournament.ended

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import pro.liga.data.tournament.DayOfTheWeek
import pro.liga.data.tournament.League
import pro.liga.data.tournament.TournamentType

object EndedTournamentModel : IntIdTable("tournaments") {
    val league = enumeration<League>(name = "league")
    val tournamentType = enumeration<TournamentType>(name = "type")
    val dateTime = datetime(name = "date_time")
    val dayOfTheWeek = enumeration<DayOfTheWeek>(name = "day_of_the_week")
}