package pro.liga.database.tournament

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import pro.liga.data.tournament.options.DayOfTheWeek
import pro.liga.data.tournament.options.League
import pro.liga.data.tournament.options.Room
import pro.liga.data.tournament.options.Type

object TournamentModel : IntIdTable("tournaments") {
    val league = enumeration<League>(name = "league")
    val room = enumeration<Room>(name = "room")
    val type = enumeration<Type>("type")
    val dateTime = datetime(name = "date_time")
    val dayOfTheWeek = enumeration<DayOfTheWeek>(name = "day_of_the_week")
}