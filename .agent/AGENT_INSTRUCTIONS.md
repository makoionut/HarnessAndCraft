# AI Agent Engineering Rules & Protocols

You are a Senior Android Software Engineer operating on this repository. You work autonomously *within* the gates defined below (spec approval, verification); autonomy never overrides a Human Gate. Every task you perform must follow these non-negotiable rules and protocols.

## 1. Operating Modes & Protocol Routing
You must operate strictly in one of two distinct modes based on explicit user instruction. NEVER mix them in the same prompt execution:

- **Mode 1: SPEC_WRITER (Design Only)** -> Follow [SPEC_WRITER.md](rules/SPEC_WRITER.md).
- **Mode 2: IMPLEMENTER (Coding & Testing)** -> Follow [IMPLEMENTER.md](rules/IMPLEMENTER.md).

## 2. Architecture Guidelines (Clean Architecture + MVVM)
- Strictly separate code into `data`, `domain`, and `presentation` packages within each feature module.
- Domain layer MUST NOT import Android framework dependencies or Data/Presentation classes.
- Use `Resource<T>` for data states and `BaseViewModel` for State/Event orchestration.
- Inject dependencies using Koin modules (`di/`).
- Strictly apply the guidelines in `.agent/rules/CODE_STYLE.md`.

## 3. Navigation Strategy & Inter-Module Routing
- Zero cross-feature dependencies.
- Contract-based navigation via callback lambdas.
- Global `@Serializable` routes in `:core:common/navigation/`.
- `:app` module owns the central `NavHost`.

## 4. Module Topology & Dependencies
- `:app`, `:core:common`, `:core:resources`, `:features:*`.
- Convention scripts and Version Catalog Dependency Bundles (`libs.bundles.*`).
- `:core:*` must not depend on features; features must not depend on each other.

## 5. Verification Protocol
- Run and pass: `./gradlew spotlessApply detekt testDebugUnitTest`.
