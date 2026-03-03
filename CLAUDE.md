# AI-DLC and Spec-Driven Development

Kiro-style Spec Driven Development implementation on AI-DLC (AI Development Life Cycle)

## Project Context

### Paths
- Steering: `.kiro/steering/`
- Specs: `.kiro/specs/`

### Steering vs Specification

**Steering** (`.kiro/steering/`) - Guide AI with project-wide rules and context
**Specs** (`.kiro/specs/`) - Formalize development process for individual features

### Active Specifications
- Check `.kiro/specs/` for active specifications
- Use `/kiro:spec-status [feature-name]` to check progress

## Development Guidelines
- Think in English, generate responses in English. All Markdown content written to project files (e.g., requirements.md, design.md, tasks.md, research.md, validation reports) MUST be written in the target language configured for this specification (see spec.json.language).

## Minimal Workflow
- Phase 0 (optional): `/kiro:steering`, `/kiro:steering-custom`
- Phase 1 (Specification):
  - `/kiro:spec-init "description"`
  - `/kiro:spec-requirements {feature}`
  - `/kiro:validate-gap {feature}` (optional: for existing codebase)
  - `/kiro:spec-design {feature} [-y]`
  - `/kiro:validate-design {feature}` (optional: design review)
  - `/kiro:spec-tasks {feature} [-y]`
- Phase 2 (Implementation): `/kiro:spec-impl {feature} [tasks]`
  - `/kiro:validate-impl {feature}` (optional: after implementation)
- Progress check: `/kiro:spec-status {feature}` (use anytime)

## Development Rules
- 3-phase approval workflow: Requirements → Design → Tasks → Implementation
- Human review required each phase; use `-y` only for intentional fast-track
- Keep steering current and verify alignment with `/kiro:spec-status`
- Follow the user's instructions precisely, and within that scope act autonomously: gather the necessary context and complete the requested work end-to-end in this run, asking questions only when essential information is missing or the instructions are critically ambiguous.

## Steering Configuration
- Load entire `.kiro/steering/` as project memory
- Default files: `product.md`, `tech.md`, `structure.md`
- Custom files are supported (managed via `/kiro:steering-custom`)

## Domain Glossary
| Term              | Definition                                           |
|---|---|
| Owner             | A registered human user of the platform             |
| Dog Profile       | breed, age, photos, temperament tags                 |
| Match             | A mutual "right swipe" between two dog owners        |
| Compatibility Score | AI score (0-100) based on breed matrix + tags      |
| Superlike         | Premium: signals strong interest before match        |

## User Personas
- **Casual Owner** — finds playmates. Low tech savvy.
- **Breeder** — professional use. Needs breed/health filtering.
- **Rescue Coordinator** — manages multiple profiles. Needs bulk ops.

## GitHub Issues sync rules
- When tasks.md is finalized: create one GitHub Issue per task
  - Title: task title from tasks.md
  - Body: acceptance criteria + link to .kiro/specs/<feature>/tasks.md
  - Label: "spec-task", milestone = feature name
  - Save the issue number back into tasks.md as a comment: <!-- gh:#42 -->
- When starting a task: add label "in-progress" to its issue
- When a task checkbox is ticked - [x]: close the issue with "Closes #N" in the commit footer
- Never close an issue without the corresponding checkbox being ticked first
- Never tick a checkbox without closing the issue (keep them atomic)

## spec-requirements rules
- When running /kiro:spec-requirements: always ask clarifying questions first
- Ask questions ONLY. Never provide answers, solutions, architecture recommendations, or implementation advice.
- Ask one question at a time, wait for the answer
- Explore "what" and "why" before "how". Don't jump to implementation.
- Never write requirements.md until the interview is complete
- Be conversational and encouraging. Make the user feel heard and supported.

If the user asks for advice or answers, respond:

> "I'm here to help you think through your product by asking questions. Let me ask you…" — then continue with a relevant question.

Systematically cover all of these through the conversation. Track what's been explored and always move toward uncovered areas:

1. Problem Space — What problem is being solved? How severe is it? How do people currently handle it?
2. Users & Stakeholders — Who will use this? What are their characteristics, needs, pain points? Are there multiple user types?
3. Core Value Proposition — What unique value does this provide? What makes it different from alternatives?
4. Key Features & Functionality — What are the essential capabilities? What must users be able to do?
5. User Workflows — How will users interact with the product day-to-day? What are the main journeys?
6. Success Metrics — How will success be measured? What outcomes are expected?
7. Constraints & Requirements — Technical, business, or regulatory constraints? Must-haves vs. nice-to-haves?
8. Scope & Priorities — What's in scope for v1? What comes in later iterations?