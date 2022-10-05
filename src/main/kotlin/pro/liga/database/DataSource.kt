package pro.liga.database

import org.apache.log4j.BasicConfigurator
import org.jetbrains.exposed.sql.Database

object DataSource {
    private const val URL = "jdbc:postgresql://liga-pro-database/ligapro"
    private const val DRIVER = "org.postgresql.Driver"
    private const val USER = "parser"
    private const val PASSWORD = "ZSdQbRfk1BYmPNehZ}Xd"

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
