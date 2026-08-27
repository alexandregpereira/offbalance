package br.alexandregpereira.offbalance.money

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class MoneyTest {

    @Test
    fun `sums amounts of the same currency`() {
        val total = listOf(Money(824_750), Money(320_000), Money(-45_000)).sum()

        assertEquals(Money(1_099_750), total)
    }

    @Test
    fun `sums an empty list into zero`() {
        assertEquals(Money.zero(), emptyList<Money>().sum())
    }

    @Test
    fun `refuses to sum different currencies`() {
        assertFailsWith<IllegalArgumentException> {
            Money(100, "BRL") + Money(100, "USD")
        }
    }
}

class MoneyFormatTest {

    @Test
    fun `groups thousands and keeps two decimals`() {
        assertEquals("R$ 36.182,50", Money(3_618_250).format())
    }

    @Test
    fun `puts the sign before the symbol`() {
        assertEquals("-R$ 450,00", Money(-45_000).format())
    }

    @Test
    fun `pads amounts below one unit`() {
        assertEquals("R$ 0,05", Money(5).format())
        assertEquals("R$ 0,00", Money.zero().format())
    }

    @Test
    fun `can omit the symbol`() {
        assertEquals("36.182,50", Money(3_618_250).format(withSymbol = false))
    }

    @Test
    fun `falls back to the currency code when there is no known symbol`() {
        assertEquals("US$ 10,00", Money(1_000, "USD").format())
        assertEquals("JPY 10,00", Money(1_000, "JPY").format())
    }
}
