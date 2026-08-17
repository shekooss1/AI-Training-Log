### 16/8/2026 : day 1 tasks are done by setup all programs 

1. # Tech stack 
     backend : springboot + java 
     database : postgresSQL 
      # This over other database because it is free and relational DB fits my app well 
     ML service : FastAPI
      # used python for ML because it better and fits the ecosystem

2. # Database schema management
   ddl-auto: update (temporary)
   # Using Hibernate auto-update for now while schema is still changing daily.
   # Will switch to Flyway migrations before deploying to production.

3. # postgres run on docker
     Easier to rebuild , matches later phases 

## Day 2 — Entity Modeling

**Date:** Aug 16, 2026

### Decisions made

- Expanded original scope (User, Workout, LogEntry) to support a two-role system:
  Swimmer and Coach, based on a new feature idea (coach publishes team training
  plans, swimmers log actual results).

- Final entity list: `User`, `Team`, `Workout`, `Set`, `CoachLogEntry`,
  `SwimmerLogEntry`, `PersonalBest`. Enums: `Role`, `Sex`, `Especiality`, `Stroke`.

- `Workout` represents one session's container (date, distance, especiality) and
  holds a list of `Set`s (e.g. 4x200 = 4 reps of 200m).

- `Set` holds reps, distance (per rep), rest, target, intensity. `overallDistance`
  (reps × distance) will be a calculated getter, not a stored field, to avoid
  data drift.

- `SwimmerLogEntry` represents one swimmer's actual result for a single rep
  (not per set, not per workout) — repNumber, actualTime, rpe, notes.

- `PersonalBest` stores swimmer's best time per distance+stroke combo, entered
  upfront by the swimmer. Used later by the ML layer to compare rep
  performance against PB.

- `CoachLogEntry` is the coach's note/wrapper around a published `Workout`
  (title + session date + link to the workout). Coach publishes workouts;
  swimmers log their actual results against them.

- Decided to keep `Team`/Coach role in scope for the *same* 35-day timeline
  rather than deferring to v2 — accepted increased workload on Days 6, 20-23
  as the tradeoff.

### Known gaps / deferred to Day 3

- Relations not yet wired: `Workout` ↔ `Set`, `CoachLogEntry` ↔ `Workout`,
  `User` ↔ `Team`, `User` ↔ `Workout`, `Set` ↔ `SwimmerLogEntry`, `PersonalBest`
  ↔ `User`. Temporarily removed `sets` field from `Workout` and `workout`
  field from `CoachLogEntry` to unblock Hibernate startup — must re-add with
  proper `@OneToMany`/`@ManyToOne` annotations tomorrow.

### Bugs fixed today

- `Team` entity had no `@Id` — Hibernate silently skipped creating its table
  (no error thrown). Lesson: every `@Entity` needs an `@Id`, and missing one
  fails silently, not loudly.
- `User` entity's table wasn't created — likely due to `user` being a
  reserved keyword in PostgreSQL. Fixed with explicit `@Table(name = "users")`.
- Confirmed all 7 entity tables created successfully via `docker exec` → `\dt`.

## MISSING ## 
Decide cascade types and fetch types (lazy vs eager) for each
Decide mappedBy ownership on each bidirectional relation (avoid JSON recursion)