# Feature Specification: [Feature Name]

## 1. Overview & Scope
- **Module**: `:features:[module_name]`
- **Goal**: [Brief description of the feature]
- **Target Package**: `com.example.baseapp.features.[module_name]`

## 2. Requirements & Acceptance Criteria (EARS Syntax)
**Note: All criteria must follow standard EARS clauses:**
 - Ubiquitous: The <system> shall <action>.
 - Event-driven: When <trigger>, the <system> shall <action>.
 - State-driven: While in <state>, the <system> shall <action>.
 - Unwanted: If <error/trigger>, then the <system> shall <action>.
 - Complex: While in <state>, when <trigger>, the <system> shall <action>.

## 3. Layer Architecture & Contracts

### Domain Layer (Pure Kotlin)
- **Models**: `[Data models / Value Objects]`
- **Repository Interface**: `[Interface declaration]`
- **UseCases**: `[UseCase names and operator fun invoke signatures]`

### Data Layer
- **Repository Implementation**: `[Class name]`
- **Data Sources**: `[Remote API service, Local DataStore/Room]`
- **DTOs & Mappers**: `[DTOs and mapping functions]`

### Presentation Layer (Compose + MVVM)
- **ViewModel**: `[ViewModel name] : BaseViewModel<State, Event, Effect>`
- **UI State**: `data class [Name]UiState(...)`
- **UI Events / Intent**: `sealed interface [Name]UiEvent`
- **Navigation Contract**:
  - **Incoming Navigation Parameters**: `[Arguments the screen receives, e.g., itemId: String — must match the @Serializable route in :core:common/navigation/]`
  - **Route Definition**: `[Serializable object/class in :core:common/navigation/ or local]`
  - **Outgoing Callbacks**: `[e.g., onBackClick: () -> Unit, onNavigateToItem: (id: String) -> Unit]`
- **Composables**: `[Screen name and key stateless components]`

## 4. Shared Dependencies & Resources
- **Base classes in `:core:common`**: [List any new shared classes needed, e.g., extensions, wrappers]
- **Strings (`:core:resources` or local `strings.xml`)**:
    - `<string name="[feature]_[name]">[Value]</string>`

## 5. Unit Test Matrix
- **[UseCaseName]Test**:
    - `when [trigger] then [assert expected flow emission]`
- **[ViewModelName]Test**:
    - `given [initial state] when [event] then state becomes [expected state]` (using Turbine)
