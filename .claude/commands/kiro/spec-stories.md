---
description: Generate User Stories and BDD Acceptance Criteria from requirements.md
---

Read .kiro/specs/$1/requirements.md.

For every functional requirement, generate:

1. A User Story in the format:
   As a [persona], I want [capability], so that [benefit]
    - Use personas defined in the requirements
    - One story per distinct user goal

2. BDD Acceptance Criteria in Given/When/Then format:
    - Minimum 2 scenarios per story: happy path + edge case
    - Be specific and testable — no vague terms like "quickly" or "properly"
    - Reference real field names, status values, and thresholds from requirements

Write output to .kiro/specs/$1/user-stories.md