package com.example.data.game

import com.example.data.player.PlayerModel
import com.example.data.set.SetModel
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.javatime.datetime
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object GameModel: Table("games") {
    val id = integer("id")
    private val tournamentId = integer("tournament_id") references TournamentModel.id
    private val dateTime = datetime("date_time")
    private val type = bool("type")
    private val firstPlayer = integer("first_player") references PlayerModel.id
    private val secondPlayer = integer("second_player") references PlayerModel.id
    private val winnerPlayer = (integer("winner_player") references PlayerModel.id).nullable()
    private val losingPlayer = (integer("losing_player") references PlayerModel.id).nullable()
    private val countSets = integer("count_sets").nullable()
    private val countPoints = integer("count_points").nullable()
    private val firstScore = integer("first_score").nullable()
    private val secondScore = integer("second_score").nullable()
    private val firstScorePoints = integer("first_score_points").nullable()
    private val secondScorePoints = integer("second_score_points").nullable()
    private val firstSubmission = float("first_submission").nullable()
    private val secondSubmission = float("second_submission").nullable()
    private val firstSubmissionOther = float("first_submission_other").nullable()
    private val secondSubmissionOther = float("second_submission_other").nullable()

    suspend fun insert(gameDTO: GameDTO) {
        newSuspendedTransaction(Dispatchers.IO) {
            insert {
                it[id] = gameDTO.id
                it[tournamentId] = gameDTO.tournamentId
                it[dateTime] = gameDTO.dateTime
                it[type] = gameDTO.type
                it[firstPlayer] = gameDTO.players.first
                it[secondPlayer] = gameDTO.players.second
                it[winnerPlayer] = gameDTO.winningPlayer
                it[losingPlayer] = gameDTO.losingPlayer
                it[countSets] = gameDTO.countSets
                it[countPoints] = gameDTO.countPoints
                it[firstScore] = gameDTO.score.first
                it[secondScore] = gameDTO.score.second
                it[firstScorePoints] = gameDTO.scorePoints.first
                it[secondScorePoints] = gameDTO.scorePoints.second
                it[firstSubmission] = gameDTO.playerSubmission.first
                it[secondSubmission] = gameDTO.playerSubmission.second
                it[firstSubmissionOther] = gameDTO.playerSubmissionOther.first
                it[secondSubmissionOther] = gameDTO.playerSubmissionOther.second
            }
            gameDTO.sets?.let { sets ->
                sets.forEach { setDTO ->
                    SetModel.insert(setDTO)
                }
            }
        }
    }
}