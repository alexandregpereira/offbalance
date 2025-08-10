# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Offbalance is an Android financial management application built with Kotlin and Jetpack Compose. This is a fresh project with prototype documentation showing the intended design system and UI structure for a comprehensive financial tracking app.

## Build and Development Commands

### Building the Project
```bash
# Clean build
./gradlew clean build

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Install debug version on connected device
./gradlew installDebug
```

### Testing
```bash
# Run unit tests
./gradlew test

# Run instrumentation tests (requires connected device or emulator)
./gradlew connectedAndroidTest

# Run all tests
./gradlew testDebugUnitTest connectedDebugAndroidTest
```

### Code Quality
```bash
# Run lint checks
./gradlew lint

# Generate lint report
./gradlew lintDebug
```

### Development Server
```bash
# Launch Android Studio or use command line to run on device/emulator
./gradlew installDebug
```

## Architecture

### Project Structure
- **Single Module**: Standard Android app module structure under `/app`
- **Package Structure**: `br.alexandregpereira.offbalance`
  - Main activity: `MainActivity.kt`
  - UI theming: `ui.theme` package with Material 3 theming
- **Build System**: Gradle with Kotlin DSL and Version Catalogs

### Technology Stack
- **Language**: Kotlin (JVM target 17)
- **UI Framework**: Jetpack Compose with Material 3
- **Android SDK**: Min 24, Target 36, Compile 36
- **Architecture Components**: 
  - Activity Compose
  - Lifecycle Runtime KTX
  - Core KTX

### Dependency Management
Dependencies are managed through Gradle Version Catalogs (`gradle/libs.versions.toml`):
- Jetpack Compose BOM for consistent compose versions
- Material 3 for modern UI components
- Standard Android testing libraries (JUnit, Espresso, Compose Testing)

### Design System
The project includes comprehensive prototype documentation in `/docs/prototype/` showing:
- Design system specifications (`design_system_v1.html`)
- Interactive app mockup (`interactive_app_v1.tsx`)
- UI structure guidelines
- Color schemes, typography, and component patterns for a financial app

### Theme Implementation
- Material 3 theming with dynamic color support (Android 12+)
- Dark/light theme support
- Custom color schemes defined in `ui.theme.Color.kt`
- Typography and component styling in theme package

## Development Guidelines

### Code Conventions
- Follow standard Kotlin coding conventions
- Use Jetpack Compose best practices for UI development
- Implement proper Material 3 theming patterns
- Maintain consistent package structure under `br.alexandregpereira.offbalance`

### Testing Strategy
- Unit tests for business logic
- Instrumentation tests for UI components
- Compose testing utilities for UI validation

### Build Configuration
- ProGuard rules are configured but minification is disabled in debug builds
- Edge-to-edge display support is enabled
- Standard Android build variants (debug/release)