package pro.liga.database

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import pro.liga.data.player.PlayerDTO
import pro.liga.data.player.tournament.TournamentPlayerDTO
import pro.liga.data.tournament.TournamentDTO
import pro.liga.database.player.PlayerTransaction
import pro.liga.database.player.tournament.TournamentPlayerTransaction
import pro.liga.database.tournament.TournamentTransaction
import pro.liga.di.Qualifiers.LIST_PLAYERS_DTO
import pro.liga.di.Qualifiers.LIST_TOURNAMENTS_DTO
import pro.liga.di.Qualifiers.LIST_TOURNAMENTS_PLAYERS_DTO
import pro.liga.di.appModule
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestTournamentsPlayersTable : KoinTest {

    private val tournamentTransaction by lazy { get<TournamentTransaction>() }
    private val playerTransaction by lazy { get<PlayerTransaction>() }
    private val tournamentPlayerTransaction by lazy { get<TournamentPlayerTransaction>() }

    private val listPlayersDTO by lazy { get<List<PlayerDTO>>(qualifier = LIST_PLAYERS_DTO.qualifier) }
    private val listTournamentsDTO by lazy { get<List<TournamentDTO>>(qualifier = LIST_TOURNAMENTS_DTO.qualifier) }
    private val listTournamentsPlayersDTO by lazy {
        get<List<TournamentPlayerDTO>>(qualifier = LIST_TOURNAMENTS_PLAYERS_DTO.qualifier)
    }

    private fun insertPlayers() = listPlayersDTO.forEach {
        playerTransaction.insert(playerDTO = it)
    }

    private fun insertTournaments() = listTournamentsDTO.forEach {
        tournamentTransaction.insert(tournamentDTO = it)
    }

    private fun deleteAllPlayers() = playerTransaction.deleteAll()

    private fun deleteAllTournaments() = tournamentTransaction.deleteAll()

    @Before
    fun before() {
        println("Database connect")
        DataSource.testConnectDatabase()
        deleteAllPlayers()
        deleteAllTournaments()
        insertPlayers()
        insertTournaments()
    }

    @After
    fun after() {
        playerTransaction.deleteAll()
        tournamentTransaction.deleteAll()
    }

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(appModule)
    }

    @Test
    fun testInsert() {
        listTournamentsPlayersDTO.forEach {

            tournamentPlayerTransaction.insert(tournamentPlayerDTO = it)

            val selectTournamentPlayerEntity =
                tournamentPlayerTransaction.getEntity(playerId = it.playerId, tournamentId = it.tournamentId)

            assertTrue(actual = selectTournamentPlayerEntity?.equals(it) ?: false)
        }

        playerTransaction.deleteAll()
    }
}