package pro.liga.database.tournament.set.player

import org.jetbrains.exposed.sql.transactions.transaction
import pro.liga.database.player.PlayerEntity
import pro.liga.database.tournament.set.SetEntity

class SetPlayerTransaction {

    fun getPlayerEntity(entity: SetPlayerEntity): PlayerEntity {
        return transaction {
            entity.player
        }
    }

    fun getSetEntity(entity: SetPlayerEntity): SetEntity {
        return transaction {
            entity.set
        }
    }
}