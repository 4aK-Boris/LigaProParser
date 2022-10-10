package pro.liga.data.tournament.options

enum class Month(val title: String, val fullTitle: String, val number: Int) {
    JANUARY(title = "Янв", fullTitle = "Январь", number = 1),
    FEBRUARY(title = "Фев", fullTitle = "Февраль", number = 2),
    MARCH(title = "Мар", fullTitle = "Март", number = 3),
    APRIL(title = "Апр", fullTitle = "Апрель", number = 4),
    MAY(title = "Май", fullTitle = "Май", number = 5),
    JUNE(title = "Июн", fullTitle = "Июнь", number = 6),
    JULY(title = "Июл", fullTitle = "Июль", number = 7),
    AUGUST(title = "Авг", fullTitle = "Август", number = 8),
    SEPTEMBER(title = "Сен", fullTitle = "Сентябрь", number = 9),
    OCTOBER(title = "Окт", fullTitle = "Октябрь", number = 10),
    NOVEMBER(title = "Ноя", fullTitle = "Ноябрь", number = 11),
    DECEMBER(title = "Дек", fullTitle = "Декабрь", number = 12);

    companion object {
        fun getMonthNumber(name: String) = values().first { month -> month.title == name }.number
    }
}