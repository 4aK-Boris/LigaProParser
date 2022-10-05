package pro.liga.data.player

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import pro.liga.data.rating.Rating
import pro.liga.data.rating.RatingMapper

class PlayerMapper {
    fun map(player: Player): PlayerDTO? {
        val listName = player.name.split(' ')
        val lastName = listName[0]
        val firstName = listName[1]
        val patronymic: String? = if (listName.size == 2) null else listName[2]
        val rank = player.rank.toInt()
        if (player.date.isBlank()) return null
        val date = LocalDate.parse(player.date, formatter)
        if (LocalDate.now().minusYears(1L).isAfter(date)) return null
        val rating = Rating(
            idPlayer = player.id,
            rating = player.rating
        )
        val ratingDTO = ratingMapper.map(rating)

        return PlayerDTO(
            firstName = firstName,
            lastName = lastName,
            patronymic = patronymic,
            rank = rank,
            ratingDTO = ratingDTO,
            date = date,
            id = player.id
        )
    }

    companion object {
        private val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

        private val ratingMapper = RatingMapper()
    }
}
