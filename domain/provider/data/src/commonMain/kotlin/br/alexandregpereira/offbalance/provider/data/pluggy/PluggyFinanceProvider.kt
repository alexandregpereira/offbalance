package br.alexandregpereira.offbalance.provider.data.pluggy

import br.alexandregpereira.offbalance.balance.model.Account
import br.alexandregpereira.offbalance.balance.model.AccountBalance
import br.alexandregpereira.offbalance.balance.model.AccountType
import br.alexandregpereira.offbalance.balance.model.Institution
import br.alexandregpereira.offbalance.money.Money
import br.alexandregpereira.offbalance.provider.FinanceProvider
import br.alexandregpereira.offbalance.provider.model.ProviderConnection
import br.alexandregpereira.offbalance.provider.model.ProviderConnectionStatus
import br.alexandregpereira.offbalance.provider.model.ProviderCredentials
import br.alexandregpereira.offbalance.provider.model.ProviderType
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlin.math.roundToLong
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

/**
 * Credentials issued by Pluggy for the app. They are not bundled with the build: the user fills
 * them in on the settings screen, and until then [PluggyFinanceProvider] refuses to run.
 */
data class PluggyConfig(
    val clientId: String = "",
    val clientSecret: String = "",
) {
    val isConfigured: Boolean = clientId.isNotBlank() && clientSecret.isNotBlank()
}

internal class PluggyFinanceProvider(
    private val httpClient: HttpClient,
    private val config: PluggyConfig,
    private val clock: Clock = Clock.System,
) : FinanceProvider {

    override val type: ProviderType = ProviderType.PLUGGY

    private var apiKey: String? = null

    override suspend fun getConnections(): List<ProviderConnection> = items().map { item ->
        ProviderConnection(
            id = item.id,
            providerType = type,
            institutionId = item.connector.id.toString(),
            institutionName = item.connector.name,
            status = item.status.toConnectionStatus(),
            createdAt = Instant.parse(item.createdAt),
        )
    }

    override suspend fun getInstitutions(): List<Institution> = items().map { item ->
        Institution(
            id = item.connector.id.toString(),
            name = item.connector.name,
            logoUrl = item.connector.imageUrl,
        )
    }.distinctBy { it.id }

    override suspend fun getAccounts(connectionId: String): List<Account> =
        accounts(connectionId).map { dto ->
            Account(
                id = dto.id,
                institutionId = items().first { it.id == dto.itemId }.connector.id.toString(),
                connectionId = dto.itemId,
                name = dto.name,
                type = dto.toAccountType(),
                currency = dto.currencyCode,
            )
        }

    override suspend fun getBalances(connectionId: String): List<AccountBalance> {
        val now = clock.now()
        return accounts(connectionId).map { dto ->
            AccountBalance(
                accountId = dto.id,
                amount = Money(
                    cents = (dto.balance * 100).roundToLong(),
                    currency = dto.currencyCode,
                ),
                date = now,
            )
        }
    }

    override suspend fun connect(credentials: ProviderCredentials): ProviderConnection {
        error(
            "Connecting a new Pluggy item requires the Pluggy Connect widget, which is not " +
                "implemented yet. Import an existing item instead."
        )
    }

    private suspend fun items(): List<PluggyItemDto> =
        httpClient.get("$BASE_URL/items") { authorized() }
            .body<PluggyListResponse<PluggyItemDto>>()
            .results

    private suspend fun accounts(itemId: String): List<PluggyAccountDto> =
        httpClient.get("$BASE_URL/accounts") {
            authorized()
            parameter("itemId", itemId)
        }.body<PluggyListResponse<PluggyAccountDto>>().results

    private suspend fun io.ktor.client.request.HttpRequestBuilder.authorized() {
        header("X-API-KEY", authenticate())
    }

    private suspend fun authenticate(): String = apiKey ?: run {
        check(config.isConfigured) {
            "Pluggy client id and secret are not configured. Set them on the settings screen."
        }
        httpClient.post("$BASE_URL/auth") {
            contentType(ContentType.Application.Json)
            setBody(PluggyAuthRequest(config.clientId, config.clientSecret))
        }.body<PluggyAuthResponse>().apiKey.also { apiKey = it }
    }

    private fun String.toConnectionStatus(): ProviderConnectionStatus = when (this) {
        "UPDATED" -> ProviderConnectionStatus.CONNECTED
        "UPDATING" -> ProviderConnectionStatus.UPDATING
        else -> ProviderConnectionStatus.ERROR
    }

    private fun PluggyAccountDto.toAccountType(): AccountType = when (type) {
        "BANK" -> when (subtype) {
            "SAVINGS_ACCOUNT" -> AccountType.SAVINGS
            else -> AccountType.CHECKING
        }
        "CREDIT" -> AccountType.CREDIT_CARD
        "INVESTMENT" -> AccountType.INVESTMENT
        else -> AccountType.CHECKING
    }

    private companion object {
        const val BASE_URL = "https://api.pluggy.ai"
    }
}
