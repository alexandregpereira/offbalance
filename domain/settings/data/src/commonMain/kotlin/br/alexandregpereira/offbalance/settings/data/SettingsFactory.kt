package br.alexandregpereira.offbalance.settings.data

import com.russhwolf.settings.Settings
import org.koin.core.scope.Scope

internal expect fun Scope.createSettings(): Settings
