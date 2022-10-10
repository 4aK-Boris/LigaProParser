package pro.liga.data.tournament.options

enum class Room(val title: String, val country: String, val city: String) {
    A3(title = "A3", country = "Россия", city = "Москва"),
    A4(title = "A4", country = "Россия", city = "Москва"),
    A5(title = "A5", country = "Россия", city = "Москва"),
    A6(title = "A6", country = "Россия", city = "Москва"),
    A15(title = "A15", country = "Белорусь", city = "Минск");

    companion object {
        fun getType(name: String) = values().first { type -> name.contains(other = type.title, ignoreCase = true) }
    }
}