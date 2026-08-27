package br.alexandregpereira.offbalance.settings.data

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.core.scope.Scope

internal actual fun Scope.createSettings(): Settings = SharedPreferencesSettings(
    get<Context>().getSharedPreferences("offbalance-settings", Context.MODE_PRIVATE)
)
