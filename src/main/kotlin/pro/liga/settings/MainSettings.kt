package pro.liga.settings

import io.ktor.server.application.Application
import kotlinx.serialization.Serializable
import pro.liga.settings.CountSettings.countSettingsRouting
import pro.liga.settings.DelaySettings.delaySettingsRouting

@Serializable
object MainSettings {
    var countSettings = CountSettings
    var delaySettings = DelaySettings

    fun Application.configureMainSettings() {
        countSettingsRouting()
        delaySettingsRouting()
    }
}
