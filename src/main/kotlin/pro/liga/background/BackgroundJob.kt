package pro.liga.background

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.log
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.configureBackgroundJob() {

//    val backgroundWork = BackgroundWork(log = log)
//
//    backgroundWork.firstLaunch()
//
//    routing {
//
//        with(backgroundWork) {
//
//            get("checkUpdateRatingPlayersJob") {
//                call.respond(updateRatingPlayersJobIsActive)
//            }
//
//            get("restartUpdateRatingPlayersJob") {
//                if (!updateRatingPlayersJobIsActive) {
//                    startUpdateRatingPlayersJob()
//                }
//                call.respond(updateRatingPlayersJobIsActive)
//            }
//
//            get("checkDeletePlayersJob") {
//                call.respond(deletePlayersJobIsActive)
//            }
//
//            get("restartDeletePlayersJob") {
//                if (!deletePlayersJobIsActive) {
//                    startDeletePlayersJob()
//                }
//                call.respond(deletePlayersJobIsActive)
//            }
//        }
//    }
//
//    addPlayers(backgroundWork = backgroundWork)
//
//    updateInfoOfPlayers(backgroundWork = backgroundWork)
//
//    deletePlayers()
}
