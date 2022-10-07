package pro.liga.database.player.tournament.ended

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import pro.liga.data.rating.RatingEntity.Companion.referrersOn
import pro.liga.database.player.PlayerEntity
import pro.liga.database.player.PlayerModel
import pro.liga.database.tournament.ended.EndedTournamentEntity
import pro.liga.database.tournament.ended.EndedTournamentModel

class EndedTournamentPlayerEntity(id: EntityID<Int>) : IntEntity(id) {

    var players by PlayerEntity via EndedTournamentPlayerModel
    var place by EndedTournamentPlayerModel.place

    companion object : IntEntityClass<EndedTournamentEntity>(EndedTournamentModel)
}