package pro.liga.background

import io.ktor.http.HttpStatusCode
import io.ktor.http.HttpStatusCode.Companion
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.put
import io.ktor.server.routing.routing
import pro.liga.settings.Settings

fun Application.addPlayers(backgroundWork: BackgroundWork) {
    routing {

        put("/addAllPlayers") {
            backgroundWork.countPlayers()
            for (id in 1..Settings.settings.countSettings.countPlayers) {
                backgroundWork.addPlayer(id = id)
            }
        }

        put("/addOnePlayer") {
            if (call.parameters.contains("id")) {
                val id = call.parameters["id"].orEmpty().toInt()
                backgroundWork.addPlayer(id = id)
                call.respond(status = Companion.OK, message = "Игрок добавлен!")
            } else {
                call.respond(status = HttpStatusCode.BadRequest, message = "В параметрах запроса нет id игрока!")
            }
        }
    }
}
