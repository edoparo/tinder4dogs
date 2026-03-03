---
description: Identify system-level corner cases and failure modes from design.md
---

Read .kiro/specs/$1/requirements.md,
     .kiro/specs/$1/user-stories.md, and
     .kiro/specs/$1/design.md.

Identify corner cases at the system boundary level and write results to
.kiro/specs/$1/corner-cases.md:

## 1. Input boundary cases
- Empty, null, zero, negative, max-length, special characters
- Concurrent identical requests
- Malformed or unexpected payload shapes

## 2. State & timing edge cases
- Race conditions between concurrent users or processes
- Operations that arrive out of order or are retried
- Partial failures mid-transaction (what state is left?)
- Clock skew, timezone edge cases, DST transitions

## 3. Integration failure modes
- Every external dependency (DB, queue, third-party API, file system):
  what happens if it is slow / returns an error / is unavailable?
- Cascade failure scenarios

## 4. Security edge cases
- Auth bypass attempts, privilege escalation paths
- Injection vectors specific to this data model
- Token expiry during a multi-step operation

## 5. Data edge cases
- Existing data that violates new constraints (migration risk)
- Max scale: what breaks first at 10x current load?

For each case: describe the scenario, expected system behaviour,
and whether it is covered by an existing user story or needs a new one.
