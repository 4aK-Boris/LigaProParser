package pro.liga.database.player.rating

import java.time.LocalDate
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pro.liga.data.player.rating.RatingDTO
import pro.liga.database.MainEntity
import pro.liga.database.MyLongEntity
import pro.liga.database.player.PlayerEntity

class RatingEntity(id: EntityID<Long>) : MyLongEntity<RatingDTO>(id = id, clazz = RatingDTO::class), KoinComponent {

    private val ratingTransaction = get<RatingTransaction>()

    var rating: Short by RatingModel.rating
    var date: LocalDate by RatingModel.date

    var player: PlayerEntity by PlayerEntity referencedOn RatingModel.player

    val playerId: Int
        get() = ratingTransaction.getPlayerId(entity = this)

    companion object : LongEntityClass<RatingEntity>(RatingModel)
}