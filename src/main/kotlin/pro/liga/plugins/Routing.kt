package pro.liga.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import java.io.File
import pro.liga.background.configureBackgroundJob
import pro.liga.settings.Settings.configureSettings

fun Application.configureRouting() {

    configureBackgroundJob()

    configureSettings()

    routing {
        get("/file") {
            val file = File("Settings.json")
            call.respond(file.exists())
        }
    }
}
