# AGENTS.md — CodeChallenge

This file is the entry point for all AI coding agents working on this repository.
It loads the full engineering rule set; do not bypass it.

## Rule Chain (read in order)

1. **Engineering Rules & Protocols** — `.agent/AGENT_INSTRUCTIONS.md`
   Spec-Driven Development, architecture guidelines, module topology, testing and memory protocols.
2. **Code Style & Standards** — `.agent/rules/CODE_STYLE.md`
   i18n, secrets management, Material 3, naming conventions, coroutines, verification protocol.
3. **Specs** — consult `.agent/specs/` before writing any production code.
   Use `.agent/specs/TEMPLATE_SPEC.md` as the base for new feature specs.
4. **Project State** — read `.agent/memory/STATE.md` before doing any work, and update it after completing a feature or fix.

## Project Structure (multi-module)

- `:app` — main entry point application module; contains the sole `MainActivity`, wires Koin modules, and hosts the central `AppNavHost` and `NavHostController`.
- `:core:resources` — shared design system: themes, colors, typography, shared string resources.
- `:core:common` — shared base abstractions: `BaseViewModel`, `Resource<T>`, base UseCases, and cross-feature navigation contracts.
- `:features:sample` — sample feature library module (pure UI Composables, ViewModels, Domain, and Data). MUST NOT contain an Activity.
- `gradle/common-feature.gradle.kts` — convention script; every new feature library module MUST apply it.

See `.agent/AGENT_INSTRUCTIONS.md` section 3 (Module Topology & Dependencies) for the full dependency rules.
