package pro.liga.settings

import io.ktor.server.application.Application
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import java.io.File
import pro.liga.settings.MainSettings.configureMainSettings

object Settings {

    private const val SETTINGS_FILE_NAME = "Settings.json"

    var settings = MainSettings

    suspend fun saveSettings() = writeSettings(settings)

    suspend fun createSettings() {
        val file = File(SETTINGS_FILE_NAME)
        if (!file.exists()) {
            saveSettings()
        } else {
            settings = readSettings()
        }
    }

    fun Application.configureSettings() {
        configureMainSettings()
    }

    @OptIn(ExperimentalSerializationApi::class)
    private suspend fun writeSettings(settings: MainSettings) = coroutineScope {
        withContext(Dispatchers.IO) {
            val outputStream = File(SETTINGS_FILE_NAME).outputStream()
            Json.encodeToStream(value = settings, stream = outputStream)
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun readSettings(): MainSettings {
        val file = File(SETTINGS_FILE_NAME)
        val inputStream = file.inputStream()
        return Json.decodeFromStream(stream = inputStream)
    }
}
