package pro.liga.data.tournament.ended

import com.example.data.game.GameModel
import com.example.data.tournament.player.TournamentPlayerModel
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.javatime.datetime
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object EndedTournamentModel : Table("tournaments") {
    val id = integer("id")
    private val title = varchar("title", 50)
    private val type = varchar("type", 10)
    private val dateTime = datetime("date_time")
    private val dayOfTheWeek = integer("day_of_the_week")

    override val primaryKey = PrimaryKey(id, name = "pk_tournament")

    suspend fun insert(tournamentDTO: EndedTournamentDTO) {
        newSuspendedTransaction(Dispatchers.IO) {
            insert {
                it[id] = tournamentDTO.id
                it[title] = tournamentDTO.title
                it[type] = tournamentDTO.type.title
                it[dateTime] = tournamentDTO.dateTime
                it[dayOfTheWeek] = tournamentDTO.dayOfTheWeek.order
            }
            tournamentDTO.players.forEach { tournamentPlayerDTO ->
                TournamentPlayerModel.insert(tournamentPlayerDTO)
            }
            tournamentDTO.games.forEach { gameDTO ->
                GameModel.insert(gameDTO)
            }
        }
    }
}