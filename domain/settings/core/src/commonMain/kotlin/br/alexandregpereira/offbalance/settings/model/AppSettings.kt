package br.alexandregpereira.offbalance.settings.model

import br.alexandregpereira.offbalance.money.Money
import br.alexandregpereira.offbalance.provider.model.ProviderType

data class AppSettings(
    val selectedProvider: ProviderType = ProviderType.FAKE,
    val currency: String = Money.BRL,
)
