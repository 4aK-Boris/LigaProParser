package pro.liga.database.player

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import pro.liga.data.player.PlayerDTO
import pro.liga.database.player.rating.RatingEntity
import pro.liga.database.player.rating.RatingModel

class PlayerEntity(id: EntityID<Int>) : IntEntity(id) {

    var firstName: String by PlayerModel.firstName
    var lastName: String by PlayerModel.lastName
    var patronymic: String? by PlayerModel.patronymic
    var date: LocalDate by PlayerModel.date
    var rank: Short by PlayerModel.rank

    val ratings by RatingEntity referrersOn RatingModel.player

    override fun toString(): String {
        return "id = $id, " +
                "$lastName $firstName ${patronymic.orEmpty()}, " +
                "date = ${date.format(formatter)}, " +
                "rank = $rank"
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is PlayerEntity -> {
                id.value == other.id.value
                        && firstName == other.firstName
                        && lastName == other.lastName
                        && patronymic == other.patronymic
                        && date == other.date
                        && rank == other.rank
            }

            is PlayerDTO -> {
                id.value == other.id
                        && firstName == other.firstName
                        && lastName == other.lastName
                        && patronymic == other.patronymic
                        && date == other.date
                        && rank == other.rank
            }

            else -> false
        }
    }

    override fun hashCode(): Int {
        var hashCode = id.value
        hashCode *= 31 * firstName.hashCode()
        hashCode *= 31 * lastName.hashCode()
        hashCode *= 31 * patronymic.hashCode()
        hashCode *= 31 * date.hashCode()
        return hashCode * 31 * rank
    }

    companion object : IntEntityClass<PlayerEntity>(PlayerModel) {
        private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    }
}