import kotlin.random.Random
fun main() {
    var continueProgram = true
    var direction: Pair<String, String>? = null
    var passengersCount: Int = 0
    var train: List<Wagon>? = null
    while (continueProgram) {
        println("Выберите действие:")
        println("1. Создать направление")
        println("2. Продать билеты")
        println("3. Сформировать поезд")
        println("4. Отправить поезд")
        println("Выход")

        when (readLine()?.toUpperCase()) {
            "1" -> direction = createDirection()
            "2" -> passengersCount = sellTickets()
            "3" -> train = createTrain(passengersCount)
            "4" -> sendTrain(direction, train)
            "EXIT" -> continueProgram = false
            else -> println("Некорректный ввод. Выберите верное значение.")
        }
    }
}

fun createDirection(): Pair<String, String> {
    val cities = listOf("Москва", "Барнаул", "Санкт-Петербург", "Томск", "Нижний Новгород", "Кемерово", "Иркутск", "Красноярск", "Норильск", "Курган", "Екатеринбург", "Челябинск", "Тюмень", "Омск", "Сургут")
    val sourceCity = cities.random()
    var destinationCity = sourceCity

    while (destinationCity == sourceCity) {
        destinationCity = cities.random()
    }

    println("Направление создано: $sourceCity - $destinationCity")
    return Pair(sourceCity, destinationCity)
}

fun sellTickets(): Int {
    val passengersCount = Random.nextInt(5, 202)
    println("Продано билетов: $passengersCount")
    return passengersCount
}
fun createTrain(passengersCount: Int): List<Wagon> {
    val train = mutableListOf<Wagon>()
    var passengersLeft = passengersCount

    while (passengersLeft > 0) {
        val wagonCapacity = Random.nextInt(5,26)
        val passengersInWagon = minOf(passengersLeft, wagonCapacity)
        train.add(Wagon(wagonCapacity, passengersInWagon))
        passengersLeft -= passengersInWagon
    }

    println("Поезд сформирован:")
    train.forEachIndexed { index, wagon ->
        println("Вагон ${index + 1}: вместимость - ${wagon.capacity}, пассажиров - ${wagon.passengers}")
    }

    return train
}

fun sendTrain(direction: Pair<String, String>?, train: List<Wagon>?) {
    if (direction == null || train == null) {
        println("Сначала необходимо создать направление и сформировать поезд.")
        return
    }

    println("Поезд отправлен по направлению: ${direction.first} - ${direction.second}")
    println("Состав поезда:")
    train.forEachIndexed { index, wagon ->
        println("Вагон ${index + 1}: вместимость - ${wagon.capacity}, пассажиров - ${wagon.passengers}")
    }
}

data class Wagon(val capacity: Int, val passengers: Int)