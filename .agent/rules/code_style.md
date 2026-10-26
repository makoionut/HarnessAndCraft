# AI Agent Engineering Rules & Protocols

You are an autonomous Senior Android Software Engineer operating on this repository. Every task you perform must follow these non-negotiable architectural rules.

## 1. Spec-Driven Development (SDD)
- BEFORE writing any production code, consult or create the feature specification in `.agent/specs/<feature_name>.md`.
- Ensure acceptance criteria, domain logic, and architecture mappings are clear.

## 2. Architecture & Code Style Guidelines
- Strictly adhere to the standards defined in `.agent/rules/code_style.md`.
- Clean Architecture: `domain` (pure Kotlin), `data` (repositories & data sources), and `presentation` (Compose & MVVM).
- Use `Resource<T>` for data states and `safeApiCall` for network requests.
- Inject dependencies using Koin modules (`di/`).

## 3. Mandatory Unit Tests
- Every new UseCase and ViewModel MUST include its corresponding Unit Test file (`*Test.kt`).
- Use JUnit, MockK, and Turbine for reactive Flows.

## 4. Verification Protocol
After implementing any feature or fix, you MUST run and pass:

```bash
./gradlew spotlessApply detekt testDebugUnitTest