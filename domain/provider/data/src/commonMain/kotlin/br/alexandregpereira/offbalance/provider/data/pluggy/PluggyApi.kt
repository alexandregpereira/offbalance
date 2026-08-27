package br.alexandregpereira.offbalance.provider.data.pluggy

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PluggyAuthRequest(
    val clientId: String,
    val clientSecret: String,
)

@Serializable
internal data class PluggyAuthResponse(
    val apiKey: String,
)

@Serializable
internal data class PluggyListResponse<T>(
    val results: List<T> = emptyList(),
)

@Serializable
internal data class PluggyItemDto(
    val id: String,
    val status: String,
    val createdAt: String,
    val connector: PluggyConnectorDto,
)

@Serializable
internal data class PluggyConnectorDto(
    val id: Long,
    val name: String,
    @SerialName("imageUrl") val imageUrl: String? = null,
)

@Serializable
internal data class PluggyAccountDto(
    val id: String,
    val type: String,
    val subtype: String? = null,
    val name: String,
    val balance: Double,
    val currencyCode: String,
    val itemId: String,
)
