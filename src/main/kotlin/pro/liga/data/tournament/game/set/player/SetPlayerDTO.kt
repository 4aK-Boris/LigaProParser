package pro.liga.data.tournament.game.set.player

import pro.liga.data.DTO
import pro.liga.database.player.PlayerEntity

data class SetPlayerDTO(
    val win: Boolean,
    val pointsCount: Short,
    val winSets: Short,
    val playerEntity: PlayerEntity?
): DTO
