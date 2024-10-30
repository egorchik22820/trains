fun main() {

    // список городов
    var cityList = mutableListOf<String>("Москва", "Санкт-Петербург", "Сызрань", "Оренбург",
                                        "Чебоксары", "Самара", "Орск", "Уфа", "Витебск",
                                        "Севостополь", "Ласпи", "Балаклава", "Астрахань",
                                        "Архангельск", "Курск");

    while (true) {
        println("чтобы отправить поезд, напишите 'отправить', чтобы выйти, напишите 'выход'")
        var ection = readln()

        if (ection == "отправить") {

            // создаем поезд т наполняем вагонами(все необходимые действие происходят внутри класса)
            var train = Train(cityList)
            train.AddCars()

            println("билетов продано: ${train.GetTicketsCount()}")

            //выводим вагоны
            println(train.GetRoute())
            var i = 0
            for (car in train.cars) {
                i++
                println("вагон ${i} вместимостью ${car.capacity}")
            }

            // отправляем
            println("поезд ${train.GetRoute()}, состоящий из ${train.cars.size} вагонов отправлен")

        }
        if (ection == "выход") break
        else continue
    }

}



class Train(cityList:MutableList<String>) {

    private val _cityList = cityList
    private val _route = CreateRoute(_cityList)
    private val _passengersCount = SellTickets()
    var cars = mutableListOf<Car>()

    fun GetRoute():String {
        return _route
    }

    fun GetTicketsCount():Int {
        return  _passengersCount
    }

    // добавляем необходимое кол-во вагонов
    // не работает
    fun AddCars() {
        var totalCapacity:Int = 0

        for (i in 0 until _passengersCount) {
            if (_passengersCount > totalCapacity) {
                cars.add(Car())
                totalCapacity += cars[i].capacity
            }
            else return
        }
    }

    // создаем направление, проверка с пом рекурсии
    private fun CreateRoute(cities:MutableList<String>):String {

        val city1 = cities[(0..14).random()]
        val city2 = cities[(0..14).random()]

        if (city1 != city2) return "$city1 - $city2"
        else return CreateRoute(cities)

    }

    // продаем билеты
    fun SellTickets():Int{
        return (5..201).random()
    }




    override fun toString(): String {
        return "${this._route}\n ${this.cars.toString()} totalCapacity ${cars.sumOf { it.capacity }}\n passengersCount ${this._passengersCount}"
    }

}

class Car() {
    val capacity = (5..25).random()

    override fun toString(): String {
        return this.capacity.toString()
    }
}