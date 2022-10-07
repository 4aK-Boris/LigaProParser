package com.example.data.game.player

import com.example.data.game.GameModel
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.update
import pro.liga.database.player.PlayerModel

object GamePlayerModel : Table("game_players") {
    private val id = integer("id").autoIncrement()
    private val score = integer("score").nullable()
    private val pointsScore = integer("points_score").nullable()
    private val submission = float("submission").nullable()
    private val submissionOther = float("submission_other").nullable()
    private val win = bool("win").nullable()
    private val type = integer("type")
    private val winningGames = integer("winning_games").nullable()
    private val losingGames = integer("losing_games").nullable()
    private val playingType = integer("playing_type").nullable()
    private val playerId = integer("player_id").references(
        ref = PlayerModel.id,
        onUpdate = ReferenceOption.NO_ACTION,
        onDelete = ReferenceOption.CASCADE
    )
    private val gameId = integer("game_id").references(
        ref = GameModel.id,
        onUpdate = ReferenceOption.CASCADE,
        onDelete = ReferenceOption.CASCADE
    )

    override val primaryKey = PrimaryKey(id, name = "pk_game_player")

    suspend fun insert(gamePlayerDTO: GamePlayerDTO) {
        newSuspendedTransaction {
            insert {
                it[score] = gamePlayerDTO.score
                it[pointsScore] = gamePlayerDTO.pointsScore
                it[submission] = gamePlayerDTO.submission
                it[submissionOther] = gamePlayerDTO.submissionOther
                it[win] = gamePlayerDTO.win
                it[type] = gamePlayerDTO.type
                it[winningGames] = gamePlayerDTO.winningGames
                it[losingGames] = gamePlayerDTO.losingGames
                it[playingType] = gamePlayerDTO.playingType
                it[playerId] = gamePlayerDTO.playerId
                it[gameId] = gamePlayerDTO.gameId
            }
        }
    }

    suspend fun update(gamePlayerDTO: GamePlayerDTO) {
        newSuspendedTransaction {
            update({ (gameId eq gamePlayerDTO.gameId) and (playerId eq gamePlayerDTO.playerId) }) {
                it[score] = gamePlayerDTO.score
                it[pointsScore] = gamePlayerDTO.pointsScore
                it[submission] = gamePlayerDTO.submission
                it[submissionOther] = gamePlayerDTO.submissionOther
                it[win] = gamePlayerDTO.win
                it[type] = gamePlayerDTO.type
                it[winningGames] = gamePlayerDTO.winningGames
                it[losingGames] = gamePlayerDTO.losingGames
                it[playingType] = gamePlayerDTO.playingType
            }
        }
    }

    suspend fun delete(gameId: Int, playerId: Int) {
        newSuspendedTransaction {
            deleteWhere {
                (GamePlayerModel.gameId eq gameId) and (GamePlayerModel.playerId eq playerId)
            }
        }
    }
}