# Requirements Document — Dog Matching App

## Introduction

Dog Matching App is a mobile and web platform that connects dog owners seeking compatible dogs for playmates, breeding, or companionship. Inspired by Tinder's swipe mechanic, owners create profiles for their dogs and swipe through candidate profiles. A **Match** occurs when two owners mutually "like" each other's dog. Post-match, owners can message each other and schedule in-person meetups.

The platform is offered in two tiers:
- **Free plan**: limited daily swipes, limited messages per match, basic dog characteristics, 20 km discovery radius.
- **Premium plan**: unlimited swipes, unlimited messages, full dog characteristics, AI-based compatibility scoring (0–100), 100 km discovery radius.

Owner registration requires age 16+. Dog age filtering is available to all users. Geographic filtering radius differs by plan.

---

## Requirements

### Requirement 1: Owner Registration & Authentication

**Objective:** As a dog owner, I want to register and authenticate securely, so that I can access the platform and manage my dogs' profiles.

#### Acceptance Criteria

1. When a new user submits a registration form, the Auth Service shall create an account with a verified email address and a hashed password.
2. If a user provides a date of birth indicating age below 16, the Auth Service shall reject the registration and display an age restriction message.
3. When a user submits valid login credentials, the Auth Service shall authenticate the user and issue a session token.
4. If a user provides invalid credentials, the Auth Service shall display an authentication error without revealing which specific field is incorrect.
5. When a user requests a password reset, the Auth Service shall send a reset link to the registered email address.
6. The Auth Service shall support secure logout that invalidates the active session token.

---

### Requirement 2: Dog Profile Management

**Objective:** As a dog owner, I want to create and manage profiles for each of my dogs, so that other owners can discover them as potential matches.

#### Acceptance Criteria

1. When an authenticated owner creates a dog profile, the Profile Service shall store breed, age, size, energy level, temperament, vaccination status, and health records.
2. The Profile Service shall allow an owner to upload multiple photos for each dog profile.
3. When an owner edits a dog profile, the Profile Service shall update stored data and reflect changes immediately in the discovery feed.
4. The Profile Service shall allow an owner to manage multiple dog profiles under a single account.
5. If a required field (breed, age) is missing when creating a profile, the Profile Service shall prevent saving and display a field-level validation error.
6. When an owner deletes a dog profile, the Profile Service shall remove it from the discovery feed and mark all associated active matches as closed.

---

### Requirement 3: Discovery & Swiping

**Objective:** As a dog owner, I want to browse and swipe on other dogs, so that I can express interest and find compatible matches.

#### Acceptance Criteria

1. When an owner opens the discovery feed, the Discovery Service shall display dog profiles within the owner's geographic radius (20 km for free plan, 100 km for premium plan).
2. When an owner swipes right on a dog profile, the Discovery Service shall record a "like" for that dog.
3. When an owner swipes left on a dog profile, the Discovery Service shall record a "pass" and remove that profile from the discovery feed.
4. Where the owner has a free plan, the Discovery Service shall enforce a configurable daily swipe limit.
5. If a free-plan owner reaches the daily swipe limit, the Discovery Service shall display an upgrade prompt and prevent further swiping until the following day.
6. The Discovery Service shall allow owners to filter displayed profiles by a dog age range.
7. When all profiles within the discovery radius are exhausted, the Discovery Service shall display a "no more dogs nearby" message.

---

### Requirement 4: Compatibility Scoring

**Objective:** As a premium owner, I want to see a compatibility score for each dog, so that I can make more informed swiping decisions.

#### Acceptance Criteria

1. Where the owner has a premium plan, the Discovery Service shall display a compatibility score (0–100) on each dog profile card before swiping.
2. The Compatibility Service shall compute the score based on breed matrix, temperament tags, energy level, size, and age proximity.
3. Where the owner has a free plan, the Discovery Service shall not display compatibility scores.
4. When a compatibility score is displayed, the Discovery Service shall show a brief breakdown of the contributing factors alongside the score.
5. When either dog's profile characteristics are modified, the Compatibility Service shall recompute the compatibility score.

---

### Requirement 5: Matching

**Objective:** As a dog owner, I want to be notified when a mutual match occurs, so that I can start communicating with the other owner.

#### Acceptance Criteria

1. When two owners have each swiped right on the other's dog, the Matching Service shall create a Match and send a real-time notification to both owners.
2. When a Match is created, the Matching Service shall unlock the messaging channel between the two owners.
3. The Matching Service shall display all active matches in a dedicated matches list.
4. When an owner views their matches list, the Matching Service shall show the matched dog's profile photo, name, and (where the viewer has a premium plan) the compatibility score.
5. If one owner deletes their dog profile, the Matching Service shall close all associated matches and notify the other owners involved.

---

### Requirement 6: Messaging

**Objective:** As a matched dog owner, I want to message the other owner, so that we can coordinate a meetup.

#### Acceptance Criteria

1. When an owner sends a message within a match, the Messaging Service shall deliver it to the other owner in real time.
2. Where the owner has a free plan, the Messaging Service shall enforce a configurable daily message limit per match.
3. If a free-plan owner reaches the daily message limit, the Messaging Service shall display an upgrade prompt and prevent further messaging until the following day.
4. Where the owner has a premium plan, the Messaging Service shall allow unlimited messages per match.
5. The Messaging Service shall persist the full message history for the duration of an active match.
6. If a match is closed, the Messaging Service shall make the conversation read-only for both parties.

---

### Requirement 7: Meetup Scheduling

**Objective:** As a matched dog owner, I want to propose and confirm an in-person meetup, so that our dogs can meet.

#### Acceptance Criteria

1. When an owner proposes a meetup within a match, the Scheduling Service shall send the proposal (date, time, and location) to the other owner.
2. When the receiving owner accepts a meetup proposal, the Scheduling Service shall confirm the meetup and notify both parties.
3. If the receiving owner declines a meetup proposal, the Scheduling Service shall notify the proposing owner and allow a new proposal to be submitted.
4. The Scheduling Service shall display upcoming confirmed meetups in a dedicated calendar view accessible from the match.
5. When a confirmed meetup is 24 hours away, the Scheduling Service shall send a reminder notification to both owners.
6. When an owner cancels a confirmed meetup, the Scheduling Service shall notify the other owner and mark the meetup as cancelled.

---

### Requirement 8: Subscription Plans

**Objective:** As a dog owner, I want to choose between a free and a premium plan, so that I can access the level of features that matches my needs.

#### Acceptance Criteria

1. The Subscription Service shall provide a free plan that includes: a configurable daily swipe limit, a configurable daily message limit per match, basic dog characteristics (breed and age) visible in discovery, and a 20 km discovery radius.
2. The Subscription Service shall provide a premium plan that includes: unlimited swipes, unlimited messages, full dog characteristics (breed, age, size, energy level, temperament, vaccination status, health records) visible in discovery, compatibility scoring, and a 100 km discovery radius.
3. When an owner subscribes to the premium plan, the Subscription Service shall immediately activate all premium features for their account.
4. When a premium subscription is within 7 days of expiry, the Subscription Service shall notify the owner with a renewal reminder.
5. When a premium subscription expires, the Subscription Service shall automatically downgrade the owner to the free plan and notify them.
6. When a free-plan owner attempts to access a premium-only feature, the Subscription Service shall display a paywall prompt with upgrade options.
7. The Subscription Service shall support in-app purchase for premium plan activation and renewal.
