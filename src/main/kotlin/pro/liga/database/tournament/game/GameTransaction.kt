package pro.liga.database.tournament.game

import org.jetbrains.exposed.sql.transactions.transaction
import pro.liga.database.tournament.TournamentEntity

class GameTransaction {

    fun getTournamentEntity(entity: GameEntity): TournamentEntity {
        return transaction {
            entity.tournament
        }
    }
}