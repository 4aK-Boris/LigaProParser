package pro.liga.database.tournament.game

import org.jetbrains.exposed.sql.transactions.transaction
import pro.liga.data.tournament.game.GameDTO
import pro.liga.data.tournament.game.set.SetDTO
import pro.liga.database.tournament.TournamentEntity
import pro.liga.database.tournament.game.player.GamePlayerTransaction
import pro.liga.database.tournament.game.set.SetTransaction

class GameTransaction(
    private val setTransaction: SetTransaction,
    private val gamePlayerTransaction: GamePlayerTransaction
) {

    fun getTournamentEntity(entity: GameEntity): TournamentEntity {
        return transaction {
            entity.tournament
        }
    }

    fun insert(dto: GameDTO, tournamentEntity: TournamentEntity?): GameEntity? {
        return tournamentEntity?.let {
            transaction {
                GameEntity.new(id = dto.id) {
                    ended = dto.ended
                    matchType = dto.matchType
                    dateTime = dto.dateTime
                    tournament = tournamentEntity
                }
            }.apply {
                insertSets(setsDTO = dto.sets, gameEntity = this)
            }
        }
    }

    private fun insertSets(setsDTO: List<SetDTO>?, gameEntity: GameEntity) {
        setsDTO?.forEach { dto ->
            setTransaction.insert(dto = dto, gameEntity = gameEntity)
        }
    }
}