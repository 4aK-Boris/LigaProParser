package pro.liga.database.tournament.ended

import com.example.data.tournament.player.EndedTournamentPlayerDTO
import java.time.LocalDate
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import pro.liga.data.player.PlayerDTO
import pro.liga.data.rating.RatingDTO
import pro.liga.data.tournament.ended.EndedTournamentDTO
import pro.liga.database.player.PlayerEntity
import pro.liga.database.player.PlayerTransaction

object EndedTournamentTransaction {

    fun insert(endedTournamentDTO: EndedTournamentDTO) {
        transaction {
            addLogger(StdOutSqlLogger)
            val tournament = EndedTournamentEntity.new(id = endedTournamentDTO.id) {
                league = endedTournamentDTO.league
                tournamentType = endedTournamentDTO.type
                dateTime = endedTournamentDTO.dateTime
                dayOfTheWeek = endedTournamentDTO.dayOfTheWeek
            }
            val players = insertPlayers(players = )
            tournament.players = SizedCollection(players)
        }
    }

    fun delete(id: Int) {
        transaction {
            addLogger(StdOutSqlLogger)
            EndedTournamentEntity.findById(id = id)?.delete()
        }
    }

    private fun insertPlayers(players: List<EndedTournamentPlayerDTO>): List<PlayerEntity> {
        return players.map {
            PlayerTransaction.hasPlayerEntity(id = it.playerId)?: PlayerTransaction.insert(PlayerDTO(
                firstName = "",
                lastName = "",
                patronymic = null,
                date = LocalDate.now(),
                rank = 23,
                id = 4,
                ratingDTO = RatingDTO(playerId = 4, rating = 444, date = LocalDate.now())
            ))
        }
    }
}