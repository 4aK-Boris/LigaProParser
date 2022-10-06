package pro.liga.data.tournament.ended

import com.example.data.tournament.player.EndedTournamentPlayerModel
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.javatime.datetime
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import pro.liga.data.player.PlayerModel
import pro.liga.data.tournament.DayOfTheWeek
import pro.liga.data.tournament.League
import pro.liga.data.tournament.TournamentType
import pro.liga.database.entity.EndedTournament

object EndedTournamentModel : IntIdTable("tournaments") {
    val league = enumeration<League>(name = "league")
    val type = enumeration<TournamentType>(name = "type")
    val dateTime = datetime(name = "date_time")
    val dayOfTheWeek = enumeration<DayOfTheWeek>(name = "day_of_the_week")

    suspend fun insert(tournamentDTO: EndedTournamentDTO) {
        newSuspendedTransaction(Dispatchers.IO) {
            insert {
                it[id] = tournamentDTO.id
                it[league] = tournamentDTO.league
                it[type] = tournamentDTO.type
                it[dateTime] = tournamentDTO.dateTime
                it[dayOfTheWeek] = tournamentDTO.dayOfTheWeek
            }
            tournamentDTO.players.forEach {
                if (PlayerModel.hasPlayer(it.playerId))
                EndedTournamentPlayerModel.insert(it)
            }
//            tournamentDTO.games.forEach { gameDTO ->
//                GameModel.insert(gameDTO)
//            }
        }
    }

    fun k() {
        EndedTournament.new {
            id._value
        }
    }
}