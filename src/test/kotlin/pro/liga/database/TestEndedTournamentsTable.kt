package pro.liga.database

import org.junit.Before
import org.junit.Rule
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import pro.liga.data.player.PlayerDTO
import pro.liga.data.tournament.ended.EndedTournamentDTO
import pro.liga.database.tournament.ended.EndedTournamentEntity
import pro.liga.database.tournament.ended.EndedTournamentTransaction
import pro.liga.di.appModule
import kotlin.test.assertEquals

private const val TWO = "2"
private const val THREE = "3"

class TestEndedTournamentsTable: KoinTest {

    private val endedTournamentTransaction by lazy { get<EndedTournamentTransaction>() }

    @Before
    fun before() {
        println("Database connect")
        DataSource.testConnectDatabase()
        endedTournamentTransaction.deleteAll()
    }

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(appModule)
    }

    fun testInsertOne() {
        val endedTournamentDTO = get<EndedTournamentDTO>()
        val entity = endedTournamentTransaction.insert(endedTournamentDTO = endedTournamentDTO)
        val selectEntity = endedTournamentTransaction.getEntity(id = endedTournamentDTO.id)

//        assertEquals(expected = entity.id.value, actual = selectPlayerEntity?.id?.value)
//        assertEquals(expected = entity.firstName, actual = selectPlayerEntity?.firstName)
//        assertEquals(expected = entity.lastName, actual = selectPlayerEntity?.lastName)
//        assertEquals(expected = entity.patronymic, actual = selectPlayerEntity?.patronymic)
//        assertEquals(expected = entity.rank, actual = selectPlayerEntity?.rank)
//
//        playerTransaction.delete(id = 1)
    }
}