package com.example.data.tournament.player

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import pro.liga.data.player.PlayerModel

object EndedTournamentPlayerModel: Table("tournament_players") {
    private val id = long("id").autoIncrement()
    private val place = integer("place")
    private val playerId = integer("player_id") references PlayerModel.id
    private val tournamentId = integer("tournament_id") //references TournamentModel.id

    override val primaryKey = PrimaryKey(id, name = "pk_tournament_player")

    suspend fun insert(endedTournamentPlayerDTO: EndedTournamentPlayerDTO) {
        newSuspendedTransaction(Dispatchers.IO) {
            insert {
                it[place] = endedTournamentPlayerDTO.place
                it[playerId] = endedTournamentPlayerDTO.playerId
                it[tournamentId] = endedTournamentPlayerDTO.tournamentId
            }
        }
    }
}