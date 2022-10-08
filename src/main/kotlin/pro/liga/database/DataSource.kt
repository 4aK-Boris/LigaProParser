package pro.liga.database

import org.apache.log4j.BasicConfigurator
import org.jetbrains.exposed.sql.Database

object DataSource {
    private const val URL = "jdbc:postgresql://liga-pro-database/ligapro"
    private const val DRIVER = "org.postgresql.Driver"
    private const val USER = "parser"
    private const val PASSWORD = "ZSdQbRfk1BYmPNehZ}Xd"

    private const val TEST_URL = "jdbc:postgresql://localhost:49153/ligapro"
    private const val TEST_USER = "postgres"
    private const val TEST_PASSWORD = "postgrespw"

    fun connectDatabase() {
        Database.connect(
            url = URL,
            driver = DRIVER,
            user = USER,
            password = PASSWORD
        )
        BasicConfigurator.configure()
    }

    fun testConnectDatabase() {
        Database.connect(
            url = TEST_URL,
            driver = DRIVER,
            user = TEST_USER,
            password = TEST_PASSWORD
        )
        BasicConfigurator.configure()
    }
}
