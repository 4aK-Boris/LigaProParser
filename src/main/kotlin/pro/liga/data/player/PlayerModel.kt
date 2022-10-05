package pro.liga.data.player

import java.time.LocalDate
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import org.jetbrains.exposed.sql.update
import pro.liga.data.rating.RatingModel

object PlayerModel : Table("players") {
    val id = integer("id")
    private val firstName = varchar("first_name", length = 30)
    private val lastName = varchar("last_name", length = 30)
    private val patronymic = varchar("patronymic", length = 30).nullable()
    private val date = date("date")
    private val rank = integer("rank")

    override val primaryKey = PrimaryKey(id, name = "pk_player")

    suspend fun insert(playerDTO: PlayerDTO) {
        newSuspendedTransaction {
            insert {
                it[id] = playerDTO.id
                it[firstName] = playerDTO.firstName
                it[lastName] = playerDTO.lastName
                it[patronymic] = playerDTO.patronymic
                it[date] = playerDTO.date
                it[rank] = playerDTO.rank
            }
        }
        RatingModel.insert(playerDTO.ratingDTO)
    }

    suspend fun delete(id: Int) {
        newSuspendedTransaction {
            deleteWhere { PlayerModel.id eq id }
        }
    }

    suspend fun delete(date: LocalDate) {
        newSuspendedTransaction {
            deleteWhere { PlayerModel.date less date }
        }
    }

    suspend fun deleteAll() {
        newSuspendedTransaction {
            deleteWhere { PlayerModel.id greater 0 }
        }
    }

    suspend fun update(playerDTO: PlayerDTO) {
        newSuspendedTransaction {
            update({
                PlayerModel.id eq playerDTO.id
            }) {
                it[firstName] = playerDTO.firstName
                it[lastName] = playerDTO.lastName
                it[patronymic] = playerDTO.patronymic
                it[date] = playerDTO.date
                it[rank] = playerDTO.rank
            }
        }
        RatingModel.insert(playerDTO.ratingDTO)
    }

    suspend fun hasPlayer(id: Int): Boolean {
        return suspendedTransactionAsync {
            select { PlayerModel.id eq id }.count() != 0L
        }.await()
    }

    suspend fun getListIds(): List<Int> {
        return suspendedTransactionAsync {
            slice(PlayerModel.id).selectAll().map { it[PlayerModel.id] }
        }.await()
    }
}
