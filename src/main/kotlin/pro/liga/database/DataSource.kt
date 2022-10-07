package pro.liga.database

import org.apache.log4j.BasicConfigurator
import org.jetbrains.exposed.sql.Database

object DataSource {
    private const val URL = "jdbc:postgresql://localhost:49153/ligapro"//"jdbc:postgresql://liga-pro-database/ligapro"
    private const val DRIVER = "org.postgresql.Driver"
    private const val USER = "dmitriy"//"parser"
    private const val PASSWORD = "127485ldaDENN1124"//"ZSdQbRfk1BYmPNehZ}Xd"

    fun connectDatabase() {
        Database.connect(
            url = URL,
            driver = DRIVER,
            user = USER,
            password = PASSWORD
        )
        BasicConfigurator.configure()
    }
}
