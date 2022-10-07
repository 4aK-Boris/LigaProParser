package pro.liga.background

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.delete
import io.ktor.server.routing.routing
import pro.liga.database.player.PlayerModel

fun Application.deletePlayers() {
//    routing {
//
//        delete("deleteAllPlayers") {
//            PlayerModel.deleteAll()
//        }
//
//        delete("/deletePlayer") {
//            if (call.parameters.contains("id")) {
//                val id = call.parameters["id"].orEmpty().toInt()
//                PlayerModel.delete(id = id)
//                call.respond(status = HttpStatusCode.OK, message = "Игрок удалён!")
//            } else {
//                call.respond(status = HttpStatusCode.BadRequest, message = "В параметрах запроса нет id игрока!")
//            }
//        }
//    }
}
