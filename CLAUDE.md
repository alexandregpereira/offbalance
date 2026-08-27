# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Offbalance is a Kotlin Multiplatform offline finance app focused on **account balance history and
projection**, with a **multi provider open finance connector**. The app reads only from its local
database; providers (Pluggy, Belvo, ...) are the single write path, behind one interface.

MVP scope: consolidated balance across institutions (including CDI/investments) on a dashboard, a
net worth history screen, and a settings screen to pick the provider and sync.

Active targets: **jvm (desktop) + androidTarget**. `iosMain()` exists in `buildSrc/TargetConfig.kt`
but no module calls it yet.

**A module only gets the Android target when it actually touches the Android framework.** Every
`core` module and every module with no `androidMain` sources is plain KMP —
`plugins { kotlin("multiplatform") }` with `jvmMain()` (plus `iosMain()` later), no
`com.android.library` and no `androidLibrary { }` block. Android consumers resolve the `jvm`
variant. Today only `domain/app/data` and `domain/settings/data` (SQLDelight driver and
SharedPreferences, both needing a `Context`) plus the Compose modules apply the Android plugin.

## Build and Development Commands

```bash
./gradlew build                # everything, all targets
./gradlew :app:run             # desktop app (fastest loop)
./gradlew installDebug         # Android app on a connected device/emulator
./gradlew jvmTest              # unit tests
./gradlew lint
```

`local.properties` with `sdk.dir` is required for the Android targets (gitignored).

## Architecture

The module layout mirrors `/Users/alexandregpereira/Projects/Monster-Compendium`: kebab-case
directories, fixed submodule suffixes, Koin with the plain DSL, stateless screens plus a single
`FooFeature()` that touches DI.

```
core/
  ktx/                     expect/actual getDispatcherIO()
  money/                   Money value type and its pt-BR formatting
  event/                   EventManager/Dispatcher/Listener
  state-holder/            StateHolder, UiModel, ActionHandler
  state-holder/compose/    rememberStateHolder(), launchActionEffect()
domain/
  app/data/                SQLDelight database, DAO impls, SqlDriver, Ktor client, dataModules()
  balance/{core,data}      Money/Account/Balance models, read use cases, repositories
  provider/{core,data}     FinanceProvider abstraction; Fake + Pluggy implementations
  settings/{core,data}     selected provider and currency (multiplatform-settings)
feature/
  dashboard|history|settings, each split into state-holder/ and compose/
ui/
  foundation/              design system: tokens, Material-free components
  app/                     design system showcase
app/                       Koin bootstrap, MainStateHolder, bottom bar, platform entry points
```

Dependency rules: `core/*` depends only on `core/*`; `domain:balance:core` depends on nothing;
`domain:provider:core` → `domain:balance:core`; `domain:settings:core` → `domain:provider:core`;
`domain:*:data` → its own `core`; `feature:x:compose` → its `state-holder` + `ui/*`; `app` → all.

### Conventions

- **Repositories, data sources, providers and use cases are `suspend fun`s returning the value
  directly.** The only `Flow` in the architecture is the `StateFlow` exposed by `UiModel`. Screens
  reload on init and when `SyncEventManager` emits `SyncEvent.DataChanged`.
- Use cases are `fun interface`s with a single `suspend operator fun invoke(...)`, declared in
  `domain/x/core/usecase/`. They are named for the action with **no `UseCase` suffix**
  (`GetNetWorth`, `SyncAccounts`). The implementation is an `internal class Default<Name>` in
  `domain/x/data/usecase/`, bound to the interface in that module's `di/DataModule.kt`. One per
  file on both sides. `domain/*/core` has no Koin dependency.
- Per feature: `FooState.kt` (immutable data class plus `internal fun State.transform()`
  extensions), `FooIntent.kt` (interface plus `EmptyFooIntent` for previews), `FooStateHolder.kt`,
  `di/Module.kt`. UI: `FooScreen(state, intent, contentPadding)` plus `FooFeature()`.
- State holders extend `StateHolder<State>` from `:core:state-holder` and are **single use**:
  `rememberStateHolder()` calls `onCleared()` when the screen leaves the composition, which
  cancels `scope` for good and stops the `SyncEventManager` subscription. Always register them
  with `factory { }` — a `single { }` would be handed back dead, and `scope` throws with a
  message saying so. State that must outlive the screen is hoisted into `rememberSaveable`
  (see the selected tab in `OffbalanceApp`).
- Money is always `Money(cents: Long, currency: String)` from `:core:money`, never a floating point
  type. Format with `Money.format()`. `:core:money` depends on nothing.
- Implementations are `internal`; modules cross boundaries only through `core` interfaces.
- Platform seams (`expect`/`actual`) live in the module that owns the platform code, and only that
  module declares the extra targets — see `createSqlDriver` in `:domain:app:data` and
  `createSettings` in `:domain:settings:data`.
- Internal dependencies use Gradle's type-safe project accessors, never a path string:
  `implementation(projects.domain.balance.core)`, not `implementation(project(":domain:balance:core"))`.
  They are generated from the `include()` list, so a new module is usable as soon as it is in
  `settings.gradle.kts` (kebab-case becomes camelCase: `:core:state-holder` → `projects.core.stateHolder`).
- Only declare a `jvmTest { }` block in modules that have logic to test. Contract-only modules
  (`core/ktx`, `domain/balance/core`, `domain/provider/core`, `domain/settings/core`) leave it out.
- DAO *interfaces* live in each `domain/x/data` module, their *implementations* in
  `:domain:app:data`, so the generated `OffbalanceDatabase` stays private to that module.
- Navigation is a bottom bar index in `MainState`, no navigation library.
- User facing copy is written inline in Portuguese; there is no localization layer yet.

### Technology Stack

Kotlin 2.1.20 (JVM toolchain 21), Compose Multiplatform 1.8.0 with **Material 2** used only
internally by `ui/foundation`, Koin 4, SQLDelight 2 (`app.cash.sqldelight`), Ktor 3,
kotlinx-serialization, kotlinx-datetime, multiplatform-settings. Dependencies live in
`gradle/libs.versions.toml`; build conventions in `buildSrc/src/main/kotlin/TargetConfig.kt`
(`multiplatform {}`, `androidLibrary(withCompose = ...)`, `commonMain/androidMain/jvmMain/jvmTest`).

### Design System

`ui/foundation` exposes a dark-only theme through `OffbalanceTheme` (colors, typography, spacing,
shapes) and Material-free components: `Text`, `Button`, `Card`, `TextField`, `Scaffold`, `Icon`,
`NavigationBar`, `VerticalSpace`. Material icons are re-exported as plain `ImageVector`s in
`OffbalanceIcons`. `ui/app` renders the showcase (`./gradlew :ui:app:run` on desktop).

Prototypes that the design system was derived from live in `/docs/prototype/`.
