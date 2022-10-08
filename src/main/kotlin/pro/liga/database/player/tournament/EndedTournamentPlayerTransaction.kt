package pro.liga.database.player.tournament

import com.example.data.tournament.player.EndedTournamentPlayerDTO
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pro.liga.database.player.PlayerEntity
import pro.liga.database.player.PlayerTransaction
import pro.liga.database.tournament.ended.EndedTournamentEntity
import pro.liga.database.tournament.ended.EndedTournamentTransaction

class EndedTournamentPlayerTransaction : KoinComponent {

    private val playerTransaction = get<PlayerTransaction>()
    private val endedTournamentTransaction = get<EndedTournamentTransaction>()

    fun insert(endedTournamentPlayerDTO: EndedTournamentPlayerDTO): EndedTournamentPlayerEntity? {
        return playerTransaction.getEntity(id = endedTournamentPlayerDTO.playerId)?.let {
            insert(
                endedTournamentPlayerDTO = endedTournamentPlayerDTO,
                playerEntity = it
            )
        }
    }

    fun insert(
        endedTournamentPlayerDTO: EndedTournamentPlayerDTO,
        playerEntity: PlayerEntity
    ): EndedTournamentPlayerEntity? {
        return endedTournamentTransaction.getEntity(id = endedTournamentPlayerDTO.tournamentId)?.let {
            insert(
                endedTournamentPlayerDTO = endedTournamentPlayerDTO,
                playerEntity = playerEntity,
                endedTournamentEntity = it
            )
        }
    }

    fun insert(
        endedTournamentPlayerDTO: EndedTournamentPlayerDTO,
        endedTournamentEntity: EndedTournamentEntity
    ): EndedTournamentPlayerEntity? {
        return playerTransaction.getEntity(id = endedTournamentPlayerDTO.playerId)?.let {
            insert(
                endedTournamentPlayerDTO = endedTournamentPlayerDTO,
                playerEntity = it,
                endedTournamentEntity = endedTournamentEntity
            )
        }
    }

    fun insert(
        endedTournamentPlayerDTO: EndedTournamentPlayerDTO,
        playerEntity: PlayerEntity,
        endedTournamentEntity: EndedTournamentEntity
    ): EndedTournamentPlayerEntity {
        return transaction {
            addLogger(StdOutSqlLogger)
            EndedTournamentPlayerEntity.new {
                place = endedTournamentPlayerDTO.place
                player = playerEntity
                tournament = endedTournamentEntity
            }
        }
    }
}