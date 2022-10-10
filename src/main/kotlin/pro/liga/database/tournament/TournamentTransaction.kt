package pro.liga.database.tournament

import pro.liga.data.player.tournament.TournamentPlayerDTO
import java.time.LocalDate
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pro.liga.data.player.PlayerDTO
import pro.liga.data.player.rating.RatingDTO
import pro.liga.data.tournament.TournamentDTO
import pro.liga.database.Transaction
import pro.liga.database.player.PlayerEntity
import pro.liga.database.player.PlayerTransaction

class TournamentTransaction: KoinComponent, Transaction {

    private val playerTransaction = get<PlayerTransaction>()

    fun insert(tournamentDTO: TournamentDTO): TournamentEntity {
        return transaction {
            addLogger(StdOutSqlLogger)
            TournamentEntity.new(id = tournamentDTO.id) {
                league = tournamentDTO.league
                room = tournamentDTO.room
                type = tournamentDTO.type
                dateTime = tournamentDTO.dateTime
                dayOfTheWeek = tournamentDTO.dayOfTheWeek
            }
        }
    }

    fun getEntity(id: Int): TournamentEntity? {
        return transaction {
            TournamentEntity.findById(id = id)
        }
    }

    fun hasEntity(id: Int): Boolean = getEntity(id = id) != null

    fun delete(id: Int) {
        transaction {
            addLogger(StdOutSqlLogger)
            TournamentEntity.findById(id = id)?.delete()
        }
    }

    fun deleteAll() {
        transaction {
            addLogger(StdOutSqlLogger)
            TournamentEntity.all().forEach { it.delete() }
        }
    }

    private fun insertPlayers(players: List<TournamentPlayerDTO>): List<PlayerEntity> {
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