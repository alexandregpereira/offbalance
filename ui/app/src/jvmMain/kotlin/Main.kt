/*
 * Copyright (C) 2025 Alexandre Gomes Pereira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import br.alexandregpereira.offbalance.ui.app.DesignSystemScreen
import br.alexandregpereira.offbalance.ui.foundation.OffbalanceTheme

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title =  "Offbalance Design System",
        state = rememberWindowState(
            size = DpSize(1600.dp, 900.dp)
        ),
    ) {
        OffbalanceTheme {
            DesignSystemScreen()
        }
    }
}
