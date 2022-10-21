package pro.liga.database.tournament.game

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.datetime
import pro.liga.database.tournament.TournamentModel
import pro.liga.database.tournament.game.type.FinalStateType
import pro.liga.database.tournament.game.type.MatchType
import pro.liga.database.tournament.game.type.SemiFinalStateType

object GameModel: IntIdTable(name = "games") {

    val dateTime = datetime(name = "date_time")
    val ended = bool(name = "ended")
    val matchType = enumeration<MatchType>(name = "match_type")
    val finalStateType = enumeration<FinalStateType>(name = "final_state_type")
    val semiFinalStateType = enumeration<SemiFinalStateType>("semi_final_state_type")

    val tournament = reference(
        name = "tournament_id",
        foreign = TournamentModel,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.NO_ACTION,
        fkName = "tournament"
    )
}