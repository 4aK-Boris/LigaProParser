package pro.liga.data.tournament

enum class DayOfTheWeek(val title: String, val order: Int) {
    MONDAY(title = "Понедельник", order = 1),
    TUESDAY(title = "Вторник", order = 2),
    WEDNESDAY(title = "Среда", order = 3),
    THURSDAY(title = "Четверг", order = 4),
    FRIDAY(title = "Пятница", order = 5),
    SATURDAY(title = "Суббота", order = 6),
    SUNDAY(title = "Воскресенье", order = 7);

    companion object {
        fun getDay(name: String) = values().first { it.title.contains(other = name, ignoreCase = true) }
    }
}