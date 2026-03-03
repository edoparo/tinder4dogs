# Requirements Document

## Project Description (Input)
Tinder for Dogs is a consumer-facing mobile app. Dog owners create profiles
for their dogs, upload photos, and swipe to find compatible playmates or mates

## Introduction

Tinder for Dogs is a consumer-facing mobile application enabling dog owners to create rich profiles for their dogs, browse compatible dogs nearby, and connect with other owners for playdates or breeding. The platform supports three primary user personas: Casual Owners seeking playmates, Breeders needing breed/health filtering, and Rescue Coordinators managing multiple profiles. The app includes AI-powered compatibility scoring, a swipe-based discovery interface, in-app messaging, and a premium tier with Superlike capabilities.

---

## Requirements

### Requirement 1: User Account Management

**Objective:** As a dog owner, I want to register and manage my account, so that I can access the platform and control my personal data.

#### Acceptance Criteria

1. When a new user submits a valid email, password, and display name, the App shall create a new Owner account and send an email verification link.
2. If a user attempts to register with an email already in use, the App shall display an error message indicating the email is already registered.
3. When a user submits valid credentials on the login screen, the App shall authenticate the user and navigate to the main discovery feed.
4. If a user enters invalid credentials three consecutive times, the App shall temporarily lock the account for 15 minutes and notify the user via email.
5. When a user requests a password reset, the App shall send a reset link to the registered email address valid for 30 minutes.
6. The App shall support login via Google and Apple OAuth providers.
7. When a user deletes their account, the App shall permanently remove all personal data, dog profiles, and match history within 30 days in compliance with GDPR/CCPA.

---

### Requirement 2: Dog Profile Creation and Management

**Objective:** As a dog owner, I want to create and edit detailed profiles for my dogs, so that other owners can find and evaluate compatibility.

#### Acceptance Criteria

1. When an authenticated owner creates a new dog profile, the App shall require at minimum: dog name, breed, date of birth, and at least one photo.
2. The App shall allow an owner to create and manage up to 10 dog profiles per account.
3. When an owner saves a dog profile, the App shall persist all fields including: name, breed, date of birth, sex, neutered/spayed status, weight, and up to 10 temperament tags.
4. The App shall provide a searchable breed catalog of at least 400 recognized breeds sourced from a canonical breed registry.
5. When an owner edits an existing dog profile and saves changes, the App shall update the profile immediately and reflect changes in the discovery feed within 5 minutes.
6. If an owner attempts to create a dog profile without a required field, the App shall highlight the missing field and prevent submission.
7. Where a Breeder account is active, the App shall additionally expose health certification fields (e.g., OFA, CERF test results) on the dog profile form.

---

### Requirement 3: Photo Upload and Management

**Objective:** As a dog owner, I want to upload and organize photos of my dog, so that my profile is attractive and discoverable.

#### Acceptance Criteria

1. When an owner uploads a photo, the App shall accept JPEG, PNG, and HEIC formats up to 20 MB per file.
2. The App shall allow up to 9 photos per dog profile and designate the first photo as the profile cover image.
3. When a photo is uploaded, the App shall automatically compress and resize it to web-optimized dimensions without visible quality loss.
4. If an uploaded image does not contain a detectable dog, the App shall warn the owner and request confirmation before saving.
5. When an owner reorders photos, the App shall update the display order and save changes immediately.
6. The App shall allow an owner to delete any photo from a dog profile, provided at least one photo remains.

---

### Requirement 4: Discovery Feed and Swiping

**Objective:** As a dog owner, I want to browse a curated feed of compatible dogs and swipe to express interest, so that I can find suitable matches efficiently.

#### Acceptance Criteria

1. When an authenticated owner opens the discovery feed, the App shall display dog profiles ranked by Compatibility Score (0–100) in descending order within the configured search radius.
2. The App shall calculate the Compatibility Score using breed compatibility matrix and shared/complementary temperament tags.
3. When an owner swipes right on a dog profile, the App shall record a "like" for that dog.
4. When an owner swipes left on a dog profile, the App shall record a "pass" and exclude that profile from future discovery for 30 days.
5. While the owner is swiping, the App shall preload the next 5 profiles to ensure smooth transitions without loading delays.
6. If the discovery feed has no more profiles within the search radius, the App shall display an empty state with a suggestion to expand the search radius.
7. The App shall allow owners to filter the discovery feed by: purpose (playmate / mate), distance (1–100 km), dog age range, dog size, and sex.
8. When an owner applies filters, the App shall refresh the feed immediately and persist filter preferences for the session.

---

### Requirement 5: Matching System

**Objective:** As a dog owner, I want to be notified when a mutual match occurs, so that I can start a conversation with the other owner.

#### Acceptance Criteria

1. When both owners have swiped right on each other's dog profiles, the App shall create a Match and notify both owners with a push notification and an in-app celebration animation.
2. When a Match is created, the App shall open a dedicated chat thread between the two owners in the Messages section.
3. The App shall maintain a match history listing all active matches with the matched dog's name, photo, and date of match.
4. When an owner unmatches, the App shall remove the match, delete the associated chat thread, and notify neither party.
5. If a dog profile involved in a match is deleted, the App shall automatically unmatch and remove the associated chat thread.

---

### Requirement 6: Superlike (Premium Feature)

**Objective:** As a premium subscriber, I want to send a Superlike to signal strong interest before a match occurs, so that the other owner is aware of my enthusiasm.

#### Acceptance Criteria

1. Where the Superlike feature is included (premium subscription active), the App shall display a Superlike button on each discovery profile card.
2. When an owner sends a Superlike, the App shall notify the recipient with a distinct push notification indicating a Superlike was received.
3. The App shall limit free-tier accounts to 1 Superlike per 24 hours and premium accounts to 5 Superlikes per 24 hours.
4. When the Superlike quota is exhausted, the App shall disable the Superlike button and display remaining cooldown time.
5. If the recipient swipes right after receiving a Superlike, the App shall create a Match and tag it as "Superlike Match" in both users' match history.

---

### Requirement 7: In-App Messaging

**Objective:** As a matched dog owner, I want to message the other owner in-app, so that we can coordinate a meeting safely.

#### Acceptance Criteria

1. The App shall provide a real-time messaging interface for each active match, supporting text messages up to 2,000 characters.
2. When a new message is sent, the App shall deliver it to the recipient within 2 seconds under normal network conditions.
3. The App shall display message read receipts (sent / delivered / read) for each message.
4. When an owner receives a new message while the app is in the background, the App shall deliver a push notification with the sender's name and a message preview.
5. The App shall allow owners to share photos within chat threads (up to 5 MB per image, JPEG/PNG/HEIC).
6. If a user reports a message as abusive, the App shall flag the message for moderation review and suppress further messages from that sender pending review.

---

### Requirement 8: Search Radius and Location

**Objective:** As a dog owner, I want to set my location and search radius, so that I discover dogs near me.

#### Acceptance Criteria

1. When an owner grants location permission, the App shall use GPS coordinates to determine proximity for discovery ranking.
2. The App shall allow owners to manually set a home location if they prefer not to share real-time GPS.
3. The App shall support search radius settings from 1 km to 100 km in 1 km increments.
4. If location permission is denied, the App shall fall back to the manually set home location; if neither is available, the App shall prompt the owner to configure a location before entering the discovery feed.
5. The App shall never display a user's exact GPS coordinates to other users; only approximate distance (e.g., "3 km away") shall be shown.

---

### Requirement 9: Premium Subscription and Monetisation

**Objective:** As a user, I want to subscribe to a premium tier, so that I can access advanced features and higher usage limits.

#### Acceptance Criteria

1. The App shall offer a premium subscription purchasable via in-app purchase (Apple App Store and Google Play) on a monthly or annual basis.
2. When a subscription is purchased, the App shall activate premium features (unlimited Superlikes quota increase, profile boosts, advanced filters) within 60 seconds.
3. When a subscription expires without renewal, the App shall downgrade the account to free-tier limits immediately.
4. The App shall display the current subscription status, renewal date, and available premium features in the account settings screen.
5. Where the Breeder account type is active, the App shall offer a dedicated Breeder subscription tier with health-filter and litter-listing features.

---

### Requirement 10: Notifications and Communication Preferences

**Objective:** As a dog owner, I want to control which notifications I receive, so that I am informed without being overwhelmed.

#### Acceptance Criteria

1. The App shall send push notifications for: new matches, new messages, received Superlikes, and profile activity reminders.
2. When an owner disables a notification category in settings, the App shall cease sending that category of push notification immediately.
3. The App shall support in-app notification badges on the Messages and Matches tabs reflecting unread counts.
4. If push notification permission is denied at the OS level, the App shall rely solely on in-app badges and not re-prompt more than once per 30 days.

---

### Requirement 11: Content Moderation and Safety

**Objective:** As a platform operator, I want to moderate content and ensure user safety, so that the platform remains trustworthy and compliant.

#### Acceptance Criteria

1. The App shall scan every uploaded photo using automated content moderation to detect and reject explicit or harmful imagery before it is saved.
2. When a user reports a dog profile or message, the App shall log the report with timestamp, reporter ID, and content reference, and acknowledge receipt within the UI.
3. The App shall provide a block feature: when an owner blocks another owner, the App shall hide both profiles from each other's discovery feed and prevent messaging.
4. If automated moderation flags a profile photo, the App shall quarantine the image and notify the owner to resubmit a compliant photo within 48 hours.
5. The App shall allow administrators to suspend or permanently ban accounts from a moderation dashboard.

---

### Requirement 12: Rescue Coordinator Bulk Operations

**Objective:** As a Rescue Coordinator, I want to manage multiple dog profiles efficiently, so that I can promote adoptable dogs at scale.

#### Acceptance Criteria

1. Where a Rescue Coordinator account type is active, the App shall allow management of up to 50 dog profiles per account.
2. When a Rescue Coordinator selects multiple profiles, the App shall support bulk actions: pause/activate discovery, update location, and add/remove temperament tags.
3. The App shall display a Rescue Coordinator badge on all dog profiles managed under a rescue account to increase trust visibility.
4. When a rescue dog is adopted and its profile is archived, the App shall automatically unmatch all pending connections and send a courtesy notification to matched owners.

---

### Requirement 13: Performance and Reliability

**Objective:** As a user, I want the app to be fast and reliable, so that I have a smooth experience.

#### Acceptance Criteria

1. The App shall load the discovery feed with the first 10 profiles within 2 seconds on a 4G network connection.
2. The App shall maintain 99.5% monthly uptime for core features (login, discovery, messaging).
3. If the backend is unreachable, the App shall display a clear offline error state and retry automatically when connectivity is restored.
4. The App shall support a minimum of 100,000 concurrent active users without degradation in response times.
