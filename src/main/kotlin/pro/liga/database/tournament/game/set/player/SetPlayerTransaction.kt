package pro.liga.database.tournament.game.set.player

import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import pro.liga.data.tournament.game.set.player.SetPlayerDTO
import pro.liga.database.player.PlayerEntity
import pro.liga.database.player.PlayerTransaction
import pro.liga.database.tournament.game.set.SetEntity

class SetPlayerTransaction {

    fun getPlayerEntity(entity: SetPlayerEntity): PlayerEntity {
        return transaction {
            addLogger(StdOutSqlLogger)
            entity.player
        }
    }

    fun getSetEntity(entity: SetPlayerEntity): SetEntity {
        return transaction {
            addLogger(StdOutSqlLogger)
            entity.set
        }
    }

    fun insert(dto: SetPlayerDTO, setEntity: SetEntity?): SetPlayerEntity? {
        return setEntity?.let {
            dto.playerEntity?.let { playerEntity ->
                transaction {
                    addLogger(StdOutSqlLogger)
                    SetPlayerEntity.new {
                        win = dto.win
                        pointsCount = dto.pointsCount
                        winSets = dto.winSets
                        player = playerEntity
                        set = setEntity
                    }
                }
            }
        }
    }
}