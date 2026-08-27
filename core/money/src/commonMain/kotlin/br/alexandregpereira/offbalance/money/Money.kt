package br.alexandregpereira.offbalance.money

/**
 * Monetary amount kept as an integer number of cents to avoid floating point rounding errors.
 */
data class Money(
    val cents: Long,
    val currency: String = BRL,
) {

    operator fun plus(other: Money): Money {
        require(currency == other.currency) {
            "Cannot sum $currency with ${other.currency}"
        }
        return copy(cents = cents + other.cents)
    }

    companion object {
        const val BRL: String = "BRL"

        fun zero(currency: String = BRL): Money = Money(cents = 0L, currency = currency)
    }
}

fun Iterable<Money>.sum(currency: String = Money.BRL): Money =
    fold(Money.zero(currency)) { total, money -> total + money }
