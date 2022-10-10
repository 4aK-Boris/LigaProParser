package pro.liga.database.player.tournament

import pro.liga.data.player.tournament.TournamentPlayerDTO
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pro.liga.database.Transaction
import pro.liga.database.player.PlayerEntity
import pro.liga.database.player.PlayerTransaction
import pro.liga.database.tournament.TournamentEntity
import pro.liga.database.tournament.TournamentTransaction

class TournamentPlayerTransaction : KoinComponent, Transaction {

    private val playerTransaction = get<PlayerTransaction>()
    private val tournamentTransaction = get<TournamentTransaction>()

    fun insert(tournamentPlayerDTO: TournamentPlayerDTO): TournamentPlayerEntity? {
        return playerTransaction.getEntity(id = tournamentPlayerDTO.playerId)?.let {
            insert(
                tournamentPlayerDTO = tournamentPlayerDTO,
                playerEntity = it
            )
        }
    }

    fun insert(
        tournamentPlayerDTO: TournamentPlayerDTO,
        playerEntity: PlayerEntity
    ): TournamentPlayerEntity? {
        return tournamentTransaction.getEntity(id = tournamentPlayerDTO.tournamentId)?.let {
            insert(
                tournamentPlayerDTO = tournamentPlayerDTO,
                playerEntity = playerEntity,
                tournamentEntity = it
            )
        }
    }

    fun insert(
        tournamentPlayerDTO: TournamentPlayerDTO,
        tournamentEntity: TournamentEntity
    ): TournamentPlayerEntity? {
        return playerTransaction.getEntity(id = tournamentPlayerDTO.playerId)?.let {
            insert(
                tournamentPlayerDTO = tournamentPlayerDTO,
                playerEntity = it,
                tournamentEntity = tournamentEntity
            )
        }
    }

    fun insert(
        tournamentPlayerDTO: TournamentPlayerDTO,
        playerEntity: PlayerEntity,
        tournamentEntity: TournamentEntity
    ): TournamentPlayerEntity {
        return transaction {
            addLogger(StdOutSqlLogger)
            TournamentPlayerEntity.new {
                place = tournamentPlayerDTO.place
                player = playerEntity
                tournament = tournamentEntity
            }
        }
    }

    fun getPlayerId(entity: TournamentPlayerEntity): Int {
        return getPlayerEntity(entity = entity).id.value
    }

    fun getTournamentId(entity: TournamentPlayerEntity): Int {
        return getTournamentEntity(entity = entity).id.value
    }

    fun getPlayerEntity(entity: TournamentPlayerEntity): PlayerEntity {
        return transaction {
            entity.player
        }
    }

    fun getTournamentEntity(entity: TournamentPlayerEntity): TournamentEntity {
        return transaction {
            entity.tournament
        }
    }

    fun getEntity(playerId: Int, tournamentId: Int): TournamentPlayerEntity? {
        return transaction {
            TournamentPlayerEntity.all().firstOrNull {
                getPlayerId(entity = it) == playerId && getTournamentId(entity = it) == tournamentId
            }
        }
    }

    fun getListTournamentEntity(tournamentId: Int): List<TournamentPlayerEntity> {
        return transaction {
            getListTournamentEntity(tournamentEntity = tournamentTransaction.getEntity(id = tournamentId))
        }
    }

    fun getListTournamentEntity(tournamentEntity: TournamentEntity?): List<TournamentPlayerEntity> {
        return transaction {
            TournamentPlayerEntity.all().filter { it.tournament == tournamentEntity }
        }
    }

    fun getListPlayerEntity(playerId: Int): List<TournamentPlayerEntity> {
        return transaction {
            getListPlayerEntity(playerEntity = playerTransaction.getEntity(id = playerId))
        }
    }

    fun getListPlayerEntity(playerEntity: PlayerEntity?): List<TournamentPlayerEntity> {
        return transaction {
            TournamentPlayerEntity.all().filter { it.player == playerEntity }
        }
    }
}