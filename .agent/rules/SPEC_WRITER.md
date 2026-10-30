# Protocol: SPEC_WRITER (Design Only Mode)

## Trigger & Scope
- **Trigger**: Activated when the user asks to design, plan, or specify a feature.
- **Scope**: Create or update the feature specification file in `.agent/specs/<feature_name>.md` using `.agent/specs/TEMPLATE_SPEC.md` as the base.

## Rules & Constraints
1. **EARS Notation Rule**: ALL functional requirements must follow standard EARS grammar patterns (e.g., using "When <trigger>...", "If <error>, then...", "While in <state>..."). Formatting can use numbered lists (REQ-01) or tables for clear test traceability.
2. **Common Elements Check**: Review existing shared abstractions in `:core:common`. Specify new reusable base classes/extensions there.
3. **Navigation & Contracts**: Explicitly define incoming navigation parameters matching `@Serializable` routes in `:core:common/navigation/` and outgoing callback lambdas.
4. **RESTRICTION**: DO NOT create, modify, or delete any source code file (`src/`, `res/`, `build.gradle.kts`, etc.). Only work within `.agent/specs/`.
5. **Human Gate**: Once the specification is drafted, STOP IMMEDIATELY and ask the user for review and approval.
