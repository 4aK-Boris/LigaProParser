package pro.liga.database.tournament.game.player

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption.CASCADE
import org.jetbrains.exposed.sql.ReferenceOption.NO_ACTION
import pro.liga.database.player.PlayerModel
import pro.liga.database.tournament.game.GameModel
import pro.liga.database.tournament.game.type.FinalStateType
import pro.liga.database.tournament.game.type.SemiFinalStateType

object GamePlayerModel: IntIdTable(name = "games_players") {

    val number = short(name = "number").nullable()
    val winSets = short(name = "win_sets").nullable()
    val winPoints = short(name = "win_points").nullable()
    val submission = float(name = "submission").nullable()

    val finalStateType = enumeration<FinalStateType>(name = "final_state_type").nullable()
    val semiFinalStateType = enumeration<SemiFinalStateType>(name = "semi_final_state_type").nullable()

    val player = reference(
        name = "player_id",
        foreign = PlayerModel,
        onDelete = CASCADE,
        onUpdate = NO_ACTION,
        fkName = "player"
    ).nullable()

    val game = reference(
        name = "game_id",
        foreign = GameModel,
        onDelete = CASCADE,
        onUpdate = NO_ACTION,
        fkName = "game"
    )
}