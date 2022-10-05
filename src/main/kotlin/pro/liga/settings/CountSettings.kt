package pro.liga.settings

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.response.respondNullable
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import io.ktor.util.pipeline.PipelineContext
import kotlinx.serialization.Serializable

@Serializable
object CountSettings {

    private const val COUNT_PLAYERS = "countPlayers"
    private const val LOADING_COUNT_PLAYERS = "loadingCountPlayers"
    private const val UPDATE_RATING_COUNT_PLAYERS = "updateRatingCountPlayers"

    var countPlayers: Int = 0
    var loadingCountPlayers: Int? = 0
    var updateRatingCountPlayers: Int? = null

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

    fun Application.countSettingsRouting() {
        routing {
            route("/$COUNT_PLAYERS") {
                get {
                    call.respond(countPlayers)
                }

                put {
                    updateValue(parametersName = COUNT_PLAYERS) { newCountPlayers ->
                        countPlayers = newCountPlayers
                        Settings.saveSettings()
                    }
                }
            }

            route("/$LOADING_COUNT_PLAYERS") {
                get {
                    call.respondNullable(loadingCountPlayers)
                }

                post {
                    val newLoadingCountPlayers = call.parameters[LOADING_COUNT_PLAYERS]?.toInt()
                    loadingCountPlayers = newLoadingCountPlayers
                    Settings.saveSettings()
                    call.respondNullable(HttpStatusCode.OK, newLoadingCountPlayers)
                }
            }

            route("/$UPDATE_RATING_COUNT_PLAYERS") {
                get {
                    call.respondNullable(updateRatingCountPlayers)
                }

                post {
                    val newUpdateRatingCountPlayers = call.parameters[UPDATE_RATING_COUNT_PLAYERS]?.toInt()
                    updateRatingCountPlayers = newUpdateRatingCountPlayers
                    Settings.saveSettings()
                    call.respondNullable(HttpStatusCode.OK, newUpdateRatingCountPlayers)
                }
            }
        }
    }
}

