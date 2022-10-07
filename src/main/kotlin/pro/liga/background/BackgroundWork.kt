package pro.liga.background

import io.ktor.util.logging.Logger
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import pro.liga.data.player.Player
import pro.liga.data.player.PlayerMapper
import pro.liga.database.player.PlayerModel
import pro.liga.data.rating.Rating
import pro.liga.data.rating.RatingMapper
import pro.liga.database.player.rating.RatingModel
import pro.liga.settings.Settings
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.nanoseconds

class BackgroundWork(private val log: Logger) {

//    private val scope = CoroutineScope(Dispatchers.IO)
//
//    private val updateRatingPlayersJob = scope.launch {
//        Settings.settings.countSettings.updateRatingCountPlayers?.let { lastId ->
//            PlayerModel.getListIds().filter { it > lastId }.forEach { id ->
//                updateRatingPlayer(id = id)
//            }
//        }
//        val dateTimeNow = LocalDateTime.now()
//        val midnight = LocalTime.MAX.toNanoOfDay()
//        val timeNow = dateTimeNow.toLocalTime().toNanoOfDay()
//        val days = DayOfWeek.SUNDAY.value - dateTimeNow.dayOfWeek.value
//        val delay = (midnight - timeNow).nanoseconds + days.days
//        withContext(Dispatchers.Default) {
//            delay(delay)
//        }
//        updateRatingPlayers()
//    }
//
//    private val deletePlayersJob = scope.launch {
//        deletePlayers()
//    }
//
//    val updateRatingPlayersJobIsActive: Boolean
//        get() = updateRatingPlayersJob.isActive
//
//    val deletePlayersJobIsActive: Boolean
//        get() = deletePlayersJob.isActive
//
//    fun startUpdateRatingPlayersJob() = updateRatingPlayersJob.start()
//
//    fun startDeletePlayersJob() = deletePlayersJob.start()
//
//    fun firstLaunch() = scope.launch {
//        Settings.createSettings()
//        countPlayers()
//        loadingPlayersInfo()
//        updateRatingPlayersJob.start()
//        deletePlayersJob.start()
//    }
//
//    private suspend fun loadingPlayersInfo() {
//        Settings.settings.countSettings.loadingCountPlayers?.let {
//            for (id in it..Settings.settings.countSettings.countPlayers) {
//                addPlayer(id = id)
//            }
//            Settings.settings.countSettings.loadingCountPlayers = null
//            Settings.saveSettings()
//        }
//    }
//
//    private suspend fun updateRatingPlayers() {
//        val delay = Settings.settings.delaySettings.updateRatingPlayersDelay.days
//        while (true) {
//            PlayerModel.getListIds().forEach { id ->
//                updateRatingPlayer(id = id)
//            }
//            Settings.settings.countSettings.updateRatingCountPlayers = null
//            Settings.saveSettings()
//            withContext(Dispatchers.Default) {
//                delay(delay)
//            }
//        }
//    }
//
//    private suspend fun deletePlayers() {
//        val delay = Settings.settings.delaySettings.deletePlayersDelay.days
//        while (true) {
//            val dateNow = LocalDate.now()
//            val oldDate = dateNow.minusYears(Settings.settings.delaySettings.storageTime.toLong())
//            PlayerModel.delete(date = oldDate)
//            withContext(Dispatchers.Default) {
//                delay(delay)
//            }
//        }
//    }
//
//    suspend fun countPlayers() {
//        var a = 1
//        var b = MAX_COUNT
//        while (b - a > 1) {
//            val newIndex = (a + b) / 2
//            val flag = checkAvailabilityPlayer(newIndex)
//            if (flag) b = newIndex else a = newIndex
//        }
//        Settings.settings.countSettings.countPlayers = a
//        Settings.saveSettings()
//    }
//
//    suspend fun addPlayer(id: Int, counter: Int = 1) {
//        try {
//            val document = id.document()
//            val name = document.name()
//            if (name == PAGE_NOT_FOUND) return
//            val infoOfPlayer = document.info()
//            if (infoOfPlayer.incorrectInfo()) return
//            val player = Player(
//                name = name,
//                rank = infoOfPlayer[0],
//                rating = infoOfPlayer[2],
//                date = infoOfPlayer[3],
//                id = id
//            )
//            val mapper = PlayerMapper()
//            val playerDTO = mapper.map(player)
//            playerDTO?.let { PlayerModel.insert(playerDTO) } ?: return
//            Settings.settings.countSettings.loadingCountPlayers = id
//            Settings.saveSettings()
//        } catch (e: Exception) {
//            log.info("Ошибка! id = $id! ${e.message}")
//            if (counter < NUMBER_OF_ATTEMPTS) addPlayer(id, counter + 1)
//        }
//    }
//
//    suspend fun updateInfoOfPlayer(id: Int, counter: Int = 1) {
//        try {
//            val document = id.document()
//            val name = document.name()
//            val hasPlayer = PlayerModel.hasPlayer(id)
//            if (name == PAGE_NOT_FOUND) {
//                if (hasPlayer) PlayerModel.delete(id)
//                return
//            }
//            val infoOfPlayer = document.info()
//            if (infoOfPlayer.incorrectInfo()) return
//            val player = Player(
//                name = name,
//                rank = infoOfPlayer[0],
//                rating = infoOfPlayer[2],
//                date = infoOfPlayer[3],
//                id = id
//            )
//            val mapper = PlayerMapper()
//            val playerDTO = mapper.map(player)
//            playerDTO?.let {
//                if (hasPlayer) {
//                    PlayerModel.update(it)
//                } else {
//                    PlayerModel.update(it)
//                }
//            } ?: return
//        } catch (e: Exception) {
//            log.info("Ошибка! id = $id! ${e.message}")
//            if (counter < NUMBER_OF_ATTEMPTS) updateInfoOfPlayer(id, counter + 1)
//        }
//    }
//
//    suspend fun updateRatingPlayer(id: Int, counter: Int = 1) {
//        try {
//            val document = id.document()
//            val name = document.name()
//            if (name == PAGE_NOT_FOUND) {
//                if (PlayerModel.hasPlayer(id)) PlayerModel.delete(id)
//                return
//            }
//            val infoOfPlayer = document.info()
//            if (infoOfPlayer.incorrectInfo()) return
//            val rating = Rating(
//                idPlayer = id,
//                rating = infoOfPlayer[2]
//            )
//            val mapper = RatingMapper()
//            val ratingDTO = mapper.map(rating)
//            RatingModel.insert(ratingDTO)
//            Settings.settings.countSettings.updateRatingCountPlayers = id
//            Settings.saveSettings()
//        } catch (e: Exception) {
//            log.info("Ошибка! id = $id! ${e.message}")
//            if (counter < NUMBER_OF_ATTEMPTS) updateRatingPlayer(id, counter + 1)
//        }
//    }
//
//    private fun checkAvailabilityPlayer(id: Int) = id.document().name() == PAGE_NOT_FOUND
//
//    private fun Int.document() = Jsoup.connect("$URL_LIGA_PRO/players/$this").get()
//
//    private fun Document.name() = this.select("div.breadcrumbs").select("li.active").text()
//
//    private fun Document.info() = this.select("table.user-rating-table").select("td").map { it.text() }
//
//    private fun List<String>.incorrectInfo() = this.isEmpty() || this[2].toInt() == 0 || this[3].isBlank()
//
//    companion object {
//        private const val NUMBER_OF_ATTEMPTS = 10
//        private const val URL_LIGA_PRO = "https://tt.sport-liga.pro"
//        private const val PAGE_NOT_FOUND = "Страница не найдена"
//        private const val MAX_COUNT = 8000
//    }
}
