# AI Agent Engineering Rules & Protocols

You are an autonomous Senior Android Software Engineer operating on this repository. Every task you perform must follow these non-negotiable architectural rules.

## 1. Architecture & Code Style Guidelines
- Strictly adhere to the standards defined in `.agent/rules/code_style.md`.
- Clean Architecture: `domain` (pure Kotlin), `data` (repositories & data sources), and `presentation` (Compose & MVVM).
- Use `Resource<T>` for data states and `safeApiCall` for network requests.
- Inject dependencies using Koin modules (`di/`).

## 2. Internationalization & String Resources (i18n)
- **NO HARDCODED STRINGS**: Any user-facing static text must be declared as a string resource in `res/values/strings.xml`.
- **Naming Convention**: Use descriptive snake_case prefixes matching the feature or component:
    - `<feature>_<component>_<description>` (e.g., `login_button_sign_in`, `user_profile_error_not_found`).
- **Compose Consumption**: Always use `stringResource(id = R.string.xxx)` inside `@Composable` functions.
- **Dynamic Formatting**: For strings with parameters, use string formats (e.g., `Hello, %1$s!`) and pass arguments via `stringResource(R.string.greeting, name)`.
- **Language**: Primary language is English, second one Spanish.
- **Domain & ViewModel Isolation**: Domain and ViewModel classes must NOT hold Android resource IDs or `Context`. If the ViewModel must emit an error or message, use a typed UI text wrapper or emit domain error codes that the UI layer maps to `stringResource`.

## 3. Security & Secrets Management
- **NO HARDCODED CREDENTIALS**: NEVER hardcode API keys, tokens, or secret URLs in Kotlin code or XML files.
- **BuildConfig Injection**: All credentials must be read from `BuildConfig.<KEY_NAME>`.
- **Secrets Setup**: When a feature requires a new credential, instruct the developer to add the key name to `secrets.default.properties` and the real value to `secrets.properties`.

## 4. Material 3 Design System & UI Components
- **Material 3 (M3) Mandatory**: All UI views, screens, components, colors, and typography MUST use Material 3 (`androidx.compose.material3`).
- **Theming & Dynamic Color**: Support Material 3 dynamic color schemes (`dynamicDarkColorScheme` / `dynamicLightColorScheme`) and defined tonal palettes in `Color.kt` and `Theme.kt`.
- **Component Consistency**: Use M3 components (`Scaffold`, `TopAppBar`, `Card`, `Button`, etc.) following official Material 3 specs.

## 5. Kotlin & Java Naming Conventions
Adhere strictly to official Kotlin and Java coding conventions:
- **Classes, Interfaces, and Companion Objects, UI Components**: UpperCamelCase (PascalCase).
    - *Sample*: `NetworkObserver`, `PaymentState`, `class Constants`
- **UI Components `@Composable`** : UpperCamelCase (PascalCase). 
    - *Sample*: `ShowListScreen`, `ShowDetailsScreen`
- **Methods and Functions**: lowerCamelCase. Note: Composable functions use UpperCamelCase.
    - *Sample (Function)*: `suspend fun fetchData()`
    - *Sample (Composable)*: `@Composable fun ShowListScreen()`
- **Local Variables and Parameters**: lowerCamelCase.
    - *Sample*: `val popularList = emptyList<Show>()`
- **Static Variables and Constants**: SCREAMING_SNAKE_CASE (for compile-time constants `val` or `const val`).
    - *Sample*: `const val MAX_PAGE_SIZE = 20`, `val DEFAULT_TIMEOUT_SECONDS = 30L`

## 6. Package Naming, Null Safety & Idiomatic Kotlin
- **Package Naming**: All lowercase, dot-separated without underscores or camelCase (e.g., `com.example.appname.domain.model`).
- **Null Safety**: Avoid non-null asserted calls (`!!`). Always prefer safe calls (`?.`), Elvis operator (`?:`), and scoping functions (`let`, `run`, `apply`, `takeIf`).
- **Immutability**: Prefer immutable properties (`val`) and collections over mutable ones (`var`, mutable state without encapsulation).
- **Visibility Modifiers**: Enforce strict encapsulation. Default to `private` or `internal` visibility.

## 7. Coroutines & Asynchronous Best Practices
- **Structured Concurrency**: Always use `viewModelScope` in ViewModels or structured coroutine scopes tied to lifecycles.
- **No GlobalScope**: `GlobalScope` is strictly prohibited.
- **Dispatcher Management**: Perform blocking operations or I/O on `Dispatchers.IO` using `withContext(Dispatchers.IO)` or inject dispatchers for testability.

## 8. Verification Protocol
After implementing any feature or fix, you MUST run and pass:

```bash
./gradlew spotlessApply detekt testDebugUnitTest
```
