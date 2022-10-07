package pro.liga.data.tournament.ended.player

import com.example.data.tournament.player.EndedTournamentPlayerDTO
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import pro.liga.database.player.PlayerModel

object EndedTournamentPlayerModel: LongIdTable("tournament_players") {
    private val place = integer("place")
    private val playerId = integer("player_id") references PlayerModel.id
    private val tournamentId = integer("tournament_id") //references TournamentModel.id


    suspend fun insert(endedTournamentPlayerDTO: EndedTournamentPlayerDTO) {
        newSuspendedTransaction(Dispatchers.IO) {
        }
    }
}