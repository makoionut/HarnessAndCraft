# Specification: [Feature Name]

## 1. Goal & Context
Briefly explain the goal of this feature and user impact.

## 2. Architecture & Layer Mapping
- **Domain**: UseCases (`[Feature]UseCase.kt`), Domain Model (`[Feature].kt`).
- **Data**: Repository Implementation (`[Feature]RepositoryImpl.kt`), Remote/Local Data Sources.
- **Presentation**: State (`[Feature]UiState.kt`), ViewModel (`[Feature]ViewModel.kt`), Screen (`[Feature]Screen.kt`).

## 3. Acceptance Criteria
- [ ] Criterion 1
- [ ] Criterion 2

## 4. Testing Strategy
- Unit tests for `[Feature]UseCaseTest.kt` (Mocking repository).
- Unit tests for `[Feature]ViewModelTest.kt` (Testing StateFlow & Events).