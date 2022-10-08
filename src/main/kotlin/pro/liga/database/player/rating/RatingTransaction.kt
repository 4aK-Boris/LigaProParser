package pro.liga.database.player.rating

import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pro.liga.data.rating.RatingDTO
import pro.liga.database.player.PlayerEntity
import pro.liga.database.player.PlayerTransaction

class RatingTransaction : KoinComponent {

    private val playerTransaction: PlayerTransaction by inject()

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

    fun player(ratingEntity: RatingEntity): PlayerEntity {
        return transaction {
            ratingEntity.player
        }
    }
}