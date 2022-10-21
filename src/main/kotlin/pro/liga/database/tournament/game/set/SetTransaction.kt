package pro.liga.database.tournament.game.set

import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import pro.liga.data.tournament.game.set.SetDTO
import pro.liga.data.tournament.game.set.player.SetPlayerDTO
import pro.liga.database.tournament.game.GameEntity
import pro.liga.database.tournament.game.set.player.SetPlayerTransaction

class SetTransaction(private val setPlayerTransaction: SetPlayerTransaction) {

    fun insert(dto: SetDTO, gameEntity: GameEntity?): SetEntity? {
        return gameEntity?.let {
            transaction {
                addLogger(StdOutSqlLogger)
                SetEntity.new {
                    order = dto.order
                    countScore = dto.countScore
                    listScore = dto.listScore
                    game = gameEntity
                }.apply {
                    insertSetPlayers(setPlayersDTO = dto.setPlayersDTO, setEntity = this)
                }
            }
        }
    }

    private fun insertSetPlayers(setPlayersDTO: List<SetPlayerDTO>, setEntity: SetEntity) {
        setPlayersDTO.forEach { dto ->
            setPlayerTransaction.insert(dto = dto, setEntity = setEntity)
        }
    }

    fun getGameEntity(entity: SetEntity): GameEntity {
        return transaction {
            addLogger(StdOutSqlLogger)
            entity.game
        }
    }
}