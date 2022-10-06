package pro.liga.data.tournament

enum class DayOfTheWeek(val title: String) {
    MONDAY(title = "Понедельник"),
    TUESDAY(title = "Вторник"),
    WEDNESDAY(title = "Среда"),
    THURSDAY(title = "Четверг"),
    FRIDAY(title = "Пятница"),
    SATURDAY(title = "Суббота"),
    SUNDAY(title = "Воскресенье");

    companion object {
        fun getDay(name: String) = values().first { it.title.contains(other = name, ignoreCase = true) }
    }
}