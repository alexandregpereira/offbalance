package br.alexandregpereira.offbalance.settings.data

import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import java.util.prefs.Preferences
import org.koin.core.scope.Scope

internal actual fun Scope.createSettings(): Settings = PreferencesSettings(
    Preferences.userRoot().node("br.alexandregpereira.offbalance")
)
