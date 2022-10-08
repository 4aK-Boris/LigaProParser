package pro.liga.database.player

import java.time.LocalDate
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import pro.liga.data.player.PlayerDTO
import pro.liga.database.player.rating.RatingEntity

class PlayerTransaction {

    fun insert(playerDTO: PlayerDTO): PlayerEntity {
        return transaction {
            addLogger(StdOutSqlLogger)
            PlayerEntity.new(id = playerDTO.id) {
                firstName = playerDTO.firstName
                lastName = playerDTO.lastName
                patronymic = playerDTO.patronymic
                date = playerDTO.date
                rank = playerDTO.rank
            }
        }
    }

    fun delete(id: Int) {
        transaction {
            addLogger(StdOutSqlLogger)
            PlayerEntity.findById(id = id)?.delete()
        }
    }

    fun delete(date: LocalDate) {
        transaction {
            addLogger(StdOutSqlLogger)
            PlayerEntity.find { PlayerModel.date less date }.forEach {
                it.delete()
            }
        }
    }

    fun deleteAll() {
        transaction {
            addLogger(StdOutSqlLogger)
            PlayerEntity.all().forEach { it.delete() }
        }
    }

    fun update(playerDTO: PlayerDTO) {
        transaction {
            addLogger(StdOutSqlLogger)
            PlayerEntity.findById(id = playerDTO.id)?.let {
                it.firstName = playerDTO.firstName
                it.lastName = playerDTO.lastName
                it.patronymic = playerDTO.patronymic
                it.date = playerDTO.date
                it.rank = playerDTO.rank
            }
        }
    }

    fun hasPlayer(id: Int): Boolean {
        return transaction {
            addLogger(StdOutSqlLogger)
            PlayerEntity.findById(id = id) != null
        }
    }

    fun getEntity(id: Int): PlayerEntity? {
        return transaction {
            addLogger(StdOutSqlLogger)
            PlayerEntity.findById(id = id)
        }
    }

    fun ratings(id: Int): List<RatingEntity>? {
        return getEntity(id = id)?.let { ratings(playerEntity = it) }
    }

    fun ratings(playerEntity: PlayerEntity): List<RatingEntity> {
        return transaction {
            playerEntity.ratings.toList()
        }
    }

    fun listPlayersId(): List<Int> {
        return transaction {
            addLogger(StdOutSqlLogger)
            PlayerEntity.all().map { it.id.value }
        }
    }
}