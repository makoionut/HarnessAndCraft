---
name: accessibility-audit
description: Provides expert guidelines, rules, and code patterns for auditing and implementing robust accessibility (a11y) across Jetpack Compose UI components and screens in Android. Use this skill when reviewing or building UI components to ensure proper TalkBack support, meaningful content descriptions, minimum touch target sizes (48dp), semantic properties, and contrast compliance.
---

# Accessibility Audit & Implementation Skill (`accessibility-audit`)

This skill defines mandatory accessibility (a11y) standards and implementation patterns for all Jetpack Compose UI components in the HarnessAndCraft project.

## 1. Core Principles & Standards

- **Inclusivity First**: Every interactive element, image, and text must be fully navigable and understandable via screen readers (TalkBack / Switch Access).
- **Material 3 Accessibility Compliance**: Leverage M3 components which have built-in accessibility semantics, but verify custom layouts and composite controls manually.

---

## 2. Mandatory Accessibility Checklist

### 2.1. Minimum Touch Target Sizes (48.dp rule)
- All interactive elements (buttons, icons with actions, chips, list items) MUST have a minimum interactive touch target of **48.dp x 48.dp**.
- Use `Modifier.minimumInteractiveComponentSize()` or ensure padding/sizing meets the 48dp requirement:
  ```kotlin
  IconButton(
      onClick = { /* action */ },
      modifier = Modifier.size(48.dp) // Ensures 48dp touch target
  ) {
      Icon(imageVector = Icons.Default.Favorite, contentDescription = stringResource(R.string.cd_favorite))
  }
  ```

### 2.2. Content Descriptions
- **Informative Images/Icons**: Every decorative or informative `Image` or `Icon` acting as a button MUST have an explicit, non-empty `contentDescription` referencing a string resource (`stringResource(R.string.xxx)`).
- **Decorative Images**: If an image is purely decorative and conveys no information, explicitly set `contentDescription = null` so TalkBack ignores it:
  ```kotlin
  AsyncImage(
      model = show.imageUrl,
      contentDescription = null, // Decorative poster image
      contentScale = ContentScale.Crop
  )
  ```

### 2.3. Semantics & Custom Modifiers
- Use semantic modifiers to group or customize TalkBack announcements:
  - `Modifier.semantics { contentDescription = "..." }`
  - `Modifier.clearAndSetSemantics { ... }` for custom composite components.
  - `Modifier.semantics(mergeDescendants = true) { }` for card containers or list items where child text and icons should be read as a single cohesive unit.

### 2.4. Typography & Spacing
- Always use **sp** (scale-independent pixels) for font sizes so text respects system font scaling preferences.
- Avoid fixed heights on containers holding text to prevent text clipping when users increase font size.

---

## 3. Accessibility Testing Protocol
- When writing UI tests (`composeTestRule`), include assertions verifying accessibility semantics:
  ```kotlin
  composeTestRule.onNodeWithContentDescription("Mark as favorite")
      .assertExists()
      .assertHasClickAction()
  ```
