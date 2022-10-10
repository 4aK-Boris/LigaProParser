package pro.liga.data.player

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pro.liga.data.player.rating.Rating
import pro.liga.data.player.rating.RatingMapper
import pro.liga.settings.Settings

class PlayerMapper : KoinComponent {

    private val ratingMapper = get<RatingMapper>()
    fun map(player: Player): PlayerDTO? {
        val (firstName, lastName, patronymic) = getName(player.name)
        val rank = player.info[RANK_NUMBER].toShort()
        val date = getDate(date = player.info[DATE_NUMBER]) ?: return null
        val rating = Rating(
            idPlayer = player.id,
            rating = player.info[RATING_NUMBER]
        )
        return PlayerDTO(
            firstName = firstName,
            lastName = lastName,
            patronymic = patronymic,
            rank = rank,
            ratingDTO = ratingMapper.map(rating),
            date = date,
            id = player.id
        )
    }

    private fun getName(name: String): Triple<String, String, String?> {
        val str = name.split(' ')
        val lastName = str[0]
        val firstName = str[1]
        val patronymic: String? = str.getOrNull(index = 2)
        return Triple(firstName, lastName, patronymic)
    }

    private fun getDate(date: String): LocalDate? {
        if (date.isBlank()) return null
        val newDate = LocalDate.parse(date, formatter)
        if (LocalDate.now().minusYears(
                Settings.settings.delaySettings.storageTime.toLong()
            ).isAfter(newDate)
        ) return null
        return newDate
    }

    companion object {
        private const val DATE_NUMBER = 3
        private const val RANK_NUMBER = 0
        private const val RATING_NUMBER = 2

        private val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    }
}
