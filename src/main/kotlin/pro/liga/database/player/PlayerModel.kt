package pro.liga.database.player

import org.jetbrains.exposed.sql.*
import java.time.LocalDate
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import pro.liga.data.player.PlayerDTO
import pro.liga.data.rating.RatingModel

object PlayerModel : IntIdTable("players") {
    val firstName = varchar("first_name", length = 30)
    val lastName = varchar("last_name", length = 30)
    val patronymic = varchar("patronymic", length = 30).nullable()
    val date = date("date")
    val rank = integer("rank")
}
