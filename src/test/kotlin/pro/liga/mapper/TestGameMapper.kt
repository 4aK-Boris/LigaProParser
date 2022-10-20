package pro.liga.mapper

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.junit.Test

class TestGameMapper {

    @Test
    fun test() {
        val id = 203561
        val url = "$URL_LIGA_PRO$GAME_PREFIX$id"
        val document = Jsoup.connect(url).get()
        val players = document.select("div.player").map { player ->
            player.firstElementChild()?.attr("href")?.removePrefix(PLAYER_PREFIX)?.toInt() ?: 0
        }
        val sets = document.select("table.points").mapNotNull { set ->
            set.firstElementChild()?.select("td")?.filterNot { point ->
                point.hasClasses()
            }?.map { point ->
                if (point.hasClass("win")) players.first() else players.last()
            }
        }
        val gamePlayers = players.map { playerId ->
            sets.count { set ->
                set.groupBy { it }.
            }
        }
        println(sets)
        println()
        println(document)
    }

    private fun List<Int>.countPoints(players: Pair<Int, Int>): Pair<Int, Int> {
        this.forEach {

        }
    }

    private fun Element.hasClasses() =
        this.hasClass(NAME_CLASS)
                || this.hasClass(SET_CLASS)
                || this.hasClass(TIMEOUT_CLASS)

    companion object {
        private const val NUMBER_OF_ATTEMPTS = 10
        private const val GAME_PREFIX = "/games/"
        private const val PLAYER_PREFIX = "players/"
        private const val URL_LIGA_PRO = "https://tt.sport-liga.pro"
        private const val PAGE_NOT_FOUND = "Страница не найдена"
        private const val MAX_COUNT = 8000

        private const val NAME_CLASS = "name"
        private const val SET_CLASS = "set"
        private const val TIMEOUT_CLASS = "timeout"
    }
}