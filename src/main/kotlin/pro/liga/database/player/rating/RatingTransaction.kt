package pro.liga.database.player.rating

import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import pro.liga.data.player.rating.RatingDTO
import pro.liga.database.Transaction
import pro.liga.database.player.PlayerEntity
import pro.liga.database.player.PlayerTransaction

class RatingTransaction : KoinComponent, Transaction {

    private val playerTransaction = get<PlayerTransaction>()

    fun insert(ratingDTO: RatingDTO): RatingEntity? {
        return transaction {
            playerTransaction.getEntity(id = ratingDTO.playerId)?.let {
                insert(ratingDTO = ratingDTO, playerEntity = it)
            }
        }
    }

    fun insert(ratingDTO: RatingDTO, playerEntity: PlayerEntity): RatingEntity {
        return transaction {
            RatingEntity.new {
                player = playerEntity
                date = ratingDTO.date
                rating = ratingDTO.rating
            }
        }
    }

    fun getPlayerEntity(entity: RatingEntity): PlayerEntity {
        return transaction {
            entity.player
        }
    }

    fun getPlayerId(entity: RatingEntity) = getPlayerEntity(entity = entity).id.value
}