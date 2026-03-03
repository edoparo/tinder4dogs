---
description: Detect ambiguities, extract NFRs, and list open questions from requirements.md
---

Read .kiro/specs/$1/requirements.md.

Perform three passes and write results to
.kiro/specs/$1/clarifications.md:

## 1. Ambiguity Report
Flag every statement that is vague, contradictory, or open to multiple
interpretations. For each one:
- Quote the original statement
- Explain why it is ambiguous
- Propose a clarified rewrite
- Mark as BLOCKING (must resolve before design) or NON-BLOCKING

## 2. NFR Extraction
Extract all non-functional requirements — explicit or implied — and
organize them under these headings:
- Performance (latency, throughput, SLA targets)
- Security (auth, encryption, compliance, data residency)
- Scalability (load, concurrency, storage growth)
- Reliability (uptime, RTO, RPO, failover)
- Observability (logging, tracing, alerting)
- Usability (accessibility, browser/device support)
- Constraints (tech stack mandates, budget, team skills)
For each NFR add: source requirement ID, acceptance threshold,
and priority (P0/P1/P2).

## 3. Open Questions
List every decision that cannot be made from the current requirements.
Format:
- Q: [question]
  Impact: [what gets blocked until this is answered]
  Owner: [who should answer — product, tech, legal, etc.]
