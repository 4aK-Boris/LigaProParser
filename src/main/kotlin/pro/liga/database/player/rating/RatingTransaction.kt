package pro.liga.database.player.rating

import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.get
import org.koin.java.KoinJavaComponent.inject
import pro.liga.data.rating.RatingDTO
import pro.liga.database.player.PlayerEntity
import pro.liga.database.player.PlayerTransaction

class RatingTransaction : KoinComponent {

    private val playerTransaction: PlayerTransaction by inject()

    fun insert(ratingDTO: RatingDTO, playerEntity: PlayerEntity? = null): RatingEntity {
        transaction {
            val newPlayerEntity = playerEntity ?: playerTransaction.hasPlayerEntity(id = ratingDTO.playerId)
            newPlayerEntity?.l
            RatingEntity.new {
                this.player = newPlayerEntity
            }
        }
    }
}