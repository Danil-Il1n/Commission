import kotlin.math.max

fun main() {
    calc("Visa", 0, 524000, 76_000)
}

fun calc(cardType: String, dayTransaction: Int = 0, monthlyTransactions: Int = 0, amount: Int) {
    val dayLimit = 150_000
    val monthLimit = 600_000
    val mastercardLimit = 75_000

    val cardCommis: Double = when (cardType) {
        "Mastercard" -> if (amount + monthlyTransactions <= mastercardLimit) 0.0 else amount * 0.006 - 20
        "Visa" -> max(35.0, amount * 0.0075)
        else -> 0.0
    }

    when {
        amount > dayLimit - dayTransaction -> println(
            "Превышен дневной лимит операций\n" + "На сегодня остаток для переводов: " + (dayLimit - dayTransaction)
        )

        amount > monthLimit - monthlyTransactions -> println(
            "Превышен лимит операций за месяц\n" + "На сегодня остаток для переводов: " + (monthLimit - monthlyTransactions)
        )

        else -> println(
            "Операция прошла успешно \n" + "Отправили " + amount + "\n" + "Комиссия составила: " + cardCommis
        )
    }

    if (amount == dayLimit || monthlyTransactions + amount >= monthLimit) {
        println("Комиссию спишем в следующем месяце")
    } else {
        println("Всего доброго!")
    }
}


