package pro.liga.data.tournament.game.set

import pro.liga.data.DTO
import pro.liga.data.tournament.game.set.player.SetPlayerDTO
import pro.liga.database.tournament.game.GameEntity

data class SetDTO(
    val order: Short,
    val countScore: Short,
    val listScore: String,
    val gameEntity: GameEntity?,
    val setPlayersDTO: List<SetPlayerDTO>
): DTO
