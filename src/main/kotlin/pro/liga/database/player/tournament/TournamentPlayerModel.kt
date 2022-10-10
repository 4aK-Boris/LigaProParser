package pro.liga.database.player.tournament

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption.CASCADE
import org.jetbrains.exposed.sql.ReferenceOption.NO_ACTION
import pro.liga.database.player.PlayerModel
import pro.liga.database.tournament.TournamentModel

object TournamentPlayerModel : IntIdTable(name = "tournament_players") {
    val tournamentId = reference(
        name = "tournament_id",
        foreign = TournamentModel,
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
    val place = integer ("place").nullable()
}