package com.example.data.tournament.player

import com.example.data.player.PlayerModel
import pro.liga.data.tournament.TournamentModel
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object TournamentPlayerModel: Table("tournament_players") {
    private val id = long("id").autoIncrement()
    private val place = integer("place")
    private val playerId = integer("player_id") references PlayerModel.id
    private val tournamentId = integer("tournament_id") references TournamentModel.id

    override val primaryKey = PrimaryKey(id, name = "pk_tournament_player")

    suspend fun insert(tournamentPlayerDTO: TournamentPlayerDTO) {
        newSuspendedTransaction(Dispatchers.IO) {
            insert {
                it[place] = tournamentPlayerDTO.place
                it[playerId] = tournamentPlayerDTO.playerId
                it[tournamentId] = tournamentPlayerDTO.tournamentId
            }
        }
    }
}