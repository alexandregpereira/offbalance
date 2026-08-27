package br.alexandregpereira.offbalance.settings.usecase

import br.alexandregpereira.offbalance.settings.model.AppSettings

fun interface GetSettings {

    suspend operator fun invoke(): AppSettings
}
