package br.alexandregpereira.offbalance.feature.settings

import br.alexandregpereira.offbalance.provider.model.ProviderType

interface SettingsIntent {

    fun onProviderClick(providerType: ProviderType)

    fun onSyncClick()

    fun onMessageDismiss()
}

class EmptySettingsIntent : SettingsIntent {

    override fun onProviderClick(providerType: ProviderType) = Unit

    override fun onSyncClick() = Unit

    override fun onMessageDismiss() = Unit
}
