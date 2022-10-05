package pro.liga.settings

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import io.ktor.util.pipeline.PipelineContext
import kotlinx.serialization.Serializable

@Serializable
object DelaySettings{

    private const val ADD_PLAYERS_DELAY = "addPlayersDelay"
    private const val UPDATE_RATING_PLAYERS_DELAY = "updateRatingPlayersDelay"
    private const val DELETE_PLAYERS_DELAY = "deletePlayersDelay"
    private const val STORAGE_TIME = "storageTime"

    private const val DEFAULT_ADD_PLAYERS_DELAY = 1
    private const val DEFAULT_UPDATE_RATING_PLAYERS_DELAY = 7
    private const val DEFAULT_DELETE_PLAYERS_DELAY = 30
    private const val DEFAULT_STORAGE_TIME = 2

    var addPlayersDelay: Int = DEFAULT_ADD_PLAYERS_DELAY
    var updateRatingPlayersDelay: Int = DEFAULT_UPDATE_RATING_PLAYERS_DELAY
    var deletePlayersDelay: Int = DEFAULT_DELETE_PLAYERS_DELAY
    var storageTime: Int = DEFAULT_STORAGE_TIME

    private suspend fun PipelineContext<Unit, ApplicationCall>.updateValue(
        parametersName: String,
        setValue: suspend (Int) -> Unit
    ) {
        if (call.parameters.contains(parametersName)) {
            val value = call.parameters[parametersName].orEmpty().toInt()
            setValue(value)
            call.respond(HttpStatusCode.OK, value)
        } else {
            call.respond(HttpStatusCode.BadRequest)
        }
    }

    fun Application.delaySettingsRouting() {
        routing {
            route("/$ADD_PLAYERS_DELAY") {
                get {
                    call.respond(addPlayersDelay)
                }

                put {
                    updateValue(parametersName = ADD_PLAYERS_DELAY) { newAddPlayersDelay ->
                        addPlayersDelay = newAddPlayersDelay
                        Settings.saveSettings()
                    }
                }
            }

            route("/$UPDATE_RATING_PLAYERS_DELAY") {
                get {
                    call.respond(updateRatingPlayersDelay)
                }

                put {
                    updateValue(parametersName = UPDATE_RATING_PLAYERS_DELAY) { newUpdateRatingPlayersDelay ->
                        updateRatingPlayersDelay = newUpdateRatingPlayersDelay
                        Settings.saveSettings()
                    }
                }
            }

            route("/$DELETE_PLAYERS_DELAY") {
                get {
                    call.respond(deletePlayersDelay)
                }

                put {
                    updateValue(parametersName = DELETE_PLAYERS_DELAY) { newDeletePlayersDelay ->
                        deletePlayersDelay = newDeletePlayersDelay
                        Settings.saveSettings()
                    }
                }
            }

            route("/$STORAGE_TIME") {
                get {
                    call.respond(storageTime)
                }

                put {
                    updateValue(parametersName = STORAGE_TIME) { newStorageTime ->
                        storageTime = newStorageTime
                        Settings.saveSettings()
                    }
                }
            }
        }
    }
}
