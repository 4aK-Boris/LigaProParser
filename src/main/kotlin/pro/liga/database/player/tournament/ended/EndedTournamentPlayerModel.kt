package pro.liga.database.player.tournament.ended

import org.jetbrains.exposed.sql.ReferenceOption.CASCADE
import org.jetbrains.exposed.sql.ReferenceOption.NO_ACTION
import org.jetbrains.exposed.sql.Table
import pro.liga.data.tournament.ended.EndedTournament
import pro.liga.database.player.PlayerModel
import pro.liga.database.tournament.ended.EndedTournamentModel

object EndedTournamentPlayerModel : Table() {
    val tournament =
        reference(name = "tournament", foreign = EndedTournamentModel, onUpdate = NO_ACTION, onDelete = CASCADE)
    val player = reference("player", PlayerModel)
    val place = integer("place")

    override val primaryKey = PrimaryKey(tournament, player, name = "pk_ended_tournament_to_player")
}