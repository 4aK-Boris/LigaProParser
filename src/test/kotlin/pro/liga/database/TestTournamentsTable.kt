package pro.liga.database

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import pro.liga.data.tournament.TournamentDTO
import pro.liga.database.tournament.TournamentTransaction
import pro.liga.di.Qualifiers
import pro.liga.di.Qualifiers.LIST_TOURNAMENTS_DTO
import pro.liga.di.appModule
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TestTournamentsTable : KoinTest {

    private val tournamentTransaction by lazy { get<TournamentTransaction>() }

    private val listTournamentsDTO by lazy {
        get<List<TournamentDTO>>(qualifier = LIST_TOURNAMENTS_DTO.qualifier)
    }

    @Before
    fun before() {
        println("Database connect")
        DataSource.testConnectDatabase()
        tournamentTransaction.deleteAll()
    }

    @After
    fun after() {
        tournamentTransaction.deleteAll()
    }

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(appModule)
    }

    @Test
    fun testInsert() {

        listTournamentsDTO.forEach {

            tournamentTransaction.insert(tournamentDTO = it)

            val selectEndedTournamentEntity = tournamentTransaction.getEntity(id = it.id)

            assertTrue(actual = selectEndedTournamentEntity?.equals(it) ?: false)
        }

        tournamentTransaction.deleteAll()
    }

    @Test
    fun testDeleteById() {

        listTournamentsDTO.forEach {

            tournamentTransaction.insert(tournamentDTO = it)

            val hasEntity = tournamentTransaction.hasEntity(id = it.id)

            assertTrue(actual = hasEntity)

            tournamentTransaction.delete(id = it.id)

            val hasEntityAfterDelete = tournamentTransaction.hasEntity(id = it.id)

            assertFalse(actual = hasEntityAfterDelete)
        }
    }

    @Test
    fun testDeleteAll() {

        listTournamentsDTO.forEach {

            tournamentTransaction.insert(tournamentDTO = it)

            val hasEntity = tournamentTransaction.hasEntity(id = it.id)

            assertTrue(actual = hasEntity)
        }

        tournamentTransaction.deleteAll()

        listTournamentsDTO.forEach {

            val hasEntity = tournamentTransaction.hasEntity(id = it.id)

            assertFalse(actual = hasEntity)
        }
    }
}