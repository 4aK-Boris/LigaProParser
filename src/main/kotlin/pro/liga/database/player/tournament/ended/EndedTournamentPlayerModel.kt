package pro.liga.database.player.tournament.ended

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption.CASCADE
import org.jetbrains.exposed.sql.ReferenceOption.NO_ACTION
import org.jetbrains.exposed.sql.Table
import pro.liga.data.tournament.ended.EndedTournament
import pro.liga.database.player.PlayerModel
import pro.liga.database.tournament.ended.EndedTournamentModel

object EndedTournamentPlayerModel : IntIdTable() {
    val tournamentId = reference(
        name = "tournament_id",
        foreign = EndedTournamentModel,
        onDelete = CASCADE,
        onUpdate = NO_ACTION,
        fkName = "tournament"
    )
    val player = reference(
        name = "player_id",
        foreign = PlayerModel,
        onDelete = CASCADE,
        onUpdate = NO_ACTION,
        fkName = "player"
    )
    val place = integer ("place")
}