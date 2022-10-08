package pro.liga.database.player

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.date

object PlayerModel : IntIdTable(name = "players") {
    val firstName = varchar(name = "first_name", length = 30)
    val lastName = varchar(name = "last_name", length = 30)
    val patronymic = varchar(name = "patronymic", length = 30).nullable()
    val date = date(name = "date")
    val rank = short(name = "rank")
}
