package br.alexandregpereira.offbalance.provider.usecase

/**
 * Pulls institutions, accounts and balances from the currently selected provider and persists
 * them locally. The app reads only from the local database, so this is the single write path.
 */
fun interface SyncAccounts {

    suspend operator fun invoke()
}
