# Protocol: IMPLEMENTER (Coding & Testing Mode)

## Preconditions & Scope
- **Precondition**: The user has explicitly reviewed and approved `.agent/specs/<feature_name>.md`. "Explicit approval" means a written message from the user in the current conversation approving that spec.
- **Scope**: Implement the approved design across Clean Architecture layers, write Unit Tests, run the verification harness, and update memory.
- **Spec Changes**: If implementation reveals the spec is wrong or incomplete, STOP, report the gap, and return to SPEC_WRITER for re-approval. Do not silently deviate.

## Architecture & Code Guidelines
- **Layer Separation**: Strictly separate code into `data`, `domain`, and `presentation` packages within each feature module.
- **Domain Layer**: Must NOT import Android framework dependencies or Data/Presentation classes.
- **State & Error Management**: Use `Resource<T>` for data states, `BaseViewModel` for State/Event orchestration, and Koin for DI.
- **Navigation**: Decoupled navigation via Navigation Compose / Navigation3 and `@Serializable` routes in `:core:common/navigation/`. Feature modules must expose navigation via callback lambdas.

## Unit Testing Standards
- **Mandatory Coverage**: Every new UseCase and ViewModel MUST include its corresponding Unit Test file (`*Test.kt`).
- **Frameworks**: Use JUnit, MockK.
- **State Testing Convention**: ViewModel tests MUST use **Turbine** (`app.cash.turbine.test`) to test StateFlow/SharedFlow emissions, states, and one-off effects robustly.
- **Coverage Criterion**: Every requirement (REQ-xx) in the approved spec must be covered by at least one test.

## Accessibility Audit Protocol (UI Features)
- **Mandatory a11y Review**: Whenever the implementation includes Compose UI components, the `IMPLEMENTER` MUST audit the UI against `.agent/skills/accessibility-audit/SKILL.md` (verifying 48dp touch targets, `contentDescription` compliance, semantics, and font scaling) as part of the final implementation checklist.

## Verification Protocol
After implementing any feature or fix, you MUST run and pass the verification command:
```bash
./gradlew spotlessApply detekt testDebugUnitTest
```
- **On Failure**: Diagnose and fix. Do not disable tests, rules, or add `@Suppress` to bypass gates.
- **Retry Limit**: Max 3 fix attempts. If it fails, stop and report to the user, do not apply workarounds.
