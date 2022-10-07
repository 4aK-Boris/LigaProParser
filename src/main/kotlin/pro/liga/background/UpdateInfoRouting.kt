package pro.liga.background

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.put
import io.ktor.server.routing.routing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pro.liga.database.player.PlayerModel
import pro.liga.settings.Settings

fun Application.updateInfoOfPlayers(backgroundWork: BackgroundWork) {

    routing {

        put("/updateInfoAllPlayers") {
            withContext(Dispatchers.IO) {
                backgroundWork.countPlayers()
                for (id in 1..Settings.settings.countSettings.countPlayers) {
                    backgroundWork.updateInfoOfPlayer(id)
                }
            }
        }

        put("/updateInfoPlayer") {
            if (call.parameters.contains("playerId")) {
                val playerId = call.parameters["playerId"].orEmpty().toInt()
                backgroundWork.updateInfoOfPlayer(id = playerId)
                call.respond(HttpStatusCode.OK, playerId)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }

        put("/updateInfoExistingPlayer") {
            withContext(Dispatchers.IO) {
                PlayerModel.getListIds().forEach { id ->
                    backgroundWork.updateInfoOfPlayer(id = id)
                }
            }
        }

        put("/updateRatingPlayers") {
            withContext(Dispatchers.IO) {
                PlayerModel.getListIds().forEach { id ->
                    backgroundWork.updateRatingPlayer(id = id)
                }
            }
        }
    }
}
