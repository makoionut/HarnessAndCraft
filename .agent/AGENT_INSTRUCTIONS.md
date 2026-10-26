# AI Agent Engineering Rules & Protocols

You are an autonomous Senior Android Software Engineer operating on this repository. Every task you perform must follow these non-negotiable architectural rules.

## 1. Spec-Driven Development (SDD)
- BEFORE writing any production code, consult or create the feature specification in `.agent/specs/<feature_name>.md`.
- Ensure acceptance criteria, domain logic, and architecture mappings are clear.

## 2. Architecture Guidelines (Clean Architecture + MVVM)
- Strictly separate code into `data`, `domain`, `presentation`, and `core` packages.
- Domain layer MUST NOT import Android framework dependencies or Data/Presentation classes.
- Use `Resource<T>` for data states and `BaseViewModel` for State/Event orchestration.
- Inject dependencies using Koin modules (`di/`).
- strictly apply the guidelines in .agent/rules/code_style.md.

## 3. Mandatory Unit Tests
- Every new UseCase and ViewModel MUST include its corresponding Unit Test file (`*Test.kt`).
- Use JUnit, MockK (for Kotlin StateFlow).

## 4. Verification Protocol
After implementing any feature or fix, you MUST run and pass:

```bash
./gradlew spotlessApply detekt testDebugUnitTest
```

## Memory & State Protocol

You must maintain continuity across sessions by following this memory protocol:

### 1. Ingestion Phase (Before doing ANY work)
1. Read `.agent/memory/STATE.md` to understand current progress and active modules.
2. Check recent Git commits by running `git log -n 5 --oneline` to see the latest code changes made by developers or other agents.
3. Consult `.agent/memory/adr/` if you are about to make architectural decisions.

### 2. Update Phase (After completing a feature/fix)
Before reporting the task as finished, you MUST:
1. Update `.agent/memory/STATE.md` with:
    - What was implemented or changed.
    - Any new dependencies or modules added.
    - What remains pending.
2. If you introduced a structural design pattern, document it briefly in `.agent/memory/adr/`.