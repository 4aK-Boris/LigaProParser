package pro.liga.background

import io.ktor.server.application.Application

fun Application.updateInfoOfPlayers(backgroundWork: BackgroundWork) {

//    routing {
//
//        put("/updateInfoAllPlayers") {
//            withContext(Dispatchers.IO) {
//                backgroundWork.countPlayers()
//                for (id in 1..Settings.settings.countSettings.countPlayers) {
//                    backgroundWork.updateInfoOfPlayer(id)
//                }
//            }
//        }
//
//        put("/updateInfoPlayer") {
//            if (call.parameters.contains("playerId")) {
//                val playerId = call.parameters["playerId"].orEmpty().toInt()
//                backgroundWork.updateInfoOfPlayer(id = playerId)
//                call.respond(HttpStatusCode.OK, playerId)
//            } else {
//                call.respond(HttpStatusCode.BadRequest)
//            }
//        }
//
//        put("/updateInfoExistingPlayer") {
//            withContext(Dispatchers.IO) {
//                PlayerModel.getListIds().forEach { id ->
//                    backgroundWork.updateInfoOfPlayer(id = id)
//                }
//            }
//        }
//
//        put("/updateRatingPlayers") {
//            withContext(Dispatchers.IO) {
//                PlayerModel.getListIds().forEach { id ->
//                    backgroundWork.updateRatingPlayer(id = id)
//                }
//            }
//        }
//    }
}
