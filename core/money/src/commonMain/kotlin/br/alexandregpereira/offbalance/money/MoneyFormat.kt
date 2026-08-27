package br.alexandregpereira.offbalance.money

import kotlin.math.absoluteValue

/**
 * Formats an amount in the pt-BR convention (`R$ 12.847,50`). Written by hand because
 * `NumberFormat` is not available in common code.
 */
fun Money.format(withSymbol: Boolean = true): String {
    val isNegative = cents < 0
    val absoluteCents = cents.absoluteValue
    val units = absoluteCents / 100
    val decimals = absoluteCents % 100

    val groupedUnits = units.toString()
        .reversed()
        .chunked(3)
        .joinToString(".")
        .reversed()

    return buildString {
        if (isNegative) append('-')
        if (withSymbol) append(currency.toSymbol()).append(' ')
        append(groupedUnits)
        append(',')
        append(decimals.toString().padStart(2, '0'))
    }
}

private fun String.toSymbol(): String = when (this) {
    "BRL" -> "R$"
    "USD" -> "US$"
    "EUR" -> "€"
    else -> this
}
