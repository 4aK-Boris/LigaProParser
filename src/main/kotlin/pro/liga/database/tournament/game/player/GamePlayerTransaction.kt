package pro.liga.database.tournament.game.player

import pro.liga.data.tournament.game.player.GamePlayerDTO
import org.jetbrains.exposed.sql.transactions.transaction
import pro.liga.database.player.PlayerEntity
import pro.liga.database.tournament.game.GameEntity

class GamePlayerTransaction {

    fun getGameEntity(entity: GamePlayerEntity): GameEntity {
        return transaction {
            entity.game
        }
    }

    fun getPlayerEntity(entity: GamePlayerEntity): PlayerEntity {
        return transaction {
            entity.player
        }
    }

    fun insert(dto: GamePlayerDTO, gameEntity: GameEntity?): GamePlayerEntity? {
        return gameEntity?.let {
            transaction {

            }
        }
    }
}