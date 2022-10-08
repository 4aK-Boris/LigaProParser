package pro.liga.database.tournament.ended

import com.example.data.tournament.player.EndedTournamentPlayerDTO
import java.time.LocalDate
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pro.liga.data.player.PlayerDTO
import pro.liga.data.rating.RatingDTO
import pro.liga.data.tournament.ended.EndedTournamentDTO
import pro.liga.database.player.PlayerEntity
import pro.liga.database.player.PlayerTransaction

class EndedTournamentTransaction: KoinComponent {

    private val playerTransaction = get<PlayerTransaction>()

    fun insert(endedTournamentDTO: EndedTournamentDTO) {
        transaction {
            addLogger(StdOutSqlLogger)
            val tournament = EndedTournamentEntity.new(id = endedTournamentDTO.id) {
                league = endedTournamentDTO.league
                tournamentType = endedTournamentDTO.tournamentType
                dateTime = endedTournamentDTO.dateTime
                dayOfTheWeek = endedTournamentDTO.dayOfTheWeek
            }

        }
    }

    fun getEntity(id: Int): EndedTournamentEntity? {
        return transaction {
            EndedTournamentEntity.findById(id = id)
        }
    }

    fun delete(id: Int) {
        transaction {
            addLogger(StdOutSqlLogger)
            EndedTournamentEntity.findById(id = id)?.delete()
        }
    }

    fun deleteAll() {
        transaction {
            addLogger(StdOutSqlLogger)
            EndedTournamentEntity.all().forEach { it.delete() }
        }
    }

    private fun insertPlayers(players: List<EndedTournamentPlayerDTO>): List<PlayerEntity> {
        return players.map {
            playerTransaction.getEntity(id = it.playerId)?: playerTransaction.insert(PlayerDTO(
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