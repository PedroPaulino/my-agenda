# MyAgenda

MyAgenda is a Java-based internal communication application designed to manage company contacts, groups, and chats.

The project was developed as part of a Java learning roadmap, with a focus on **Collections, data structures, layered architecture, repository abstraction, business rules, unit testing, and performance experimentation**.

## Overview

The system provides basic functionality for:

* Contact management
* Contact search by ID and name
* Group creation and membership management
* Group administrators and ownership
* Private chats
* Group chats
* Message management
* Repository abstraction
* Comparison between `ArrayList` and `HashMap`

The application currently uses in-memory repositories and does not persist data between executions.

## Main Features

### Contact Management

Users can:

* Register contacts
* Search contacts by UUID
* Search contacts by name
* Delete contacts

Each contact contains information such as:

* Name
* Email
* Phone
* Department
* Role
* Address
* Presentation

### Group Management

Groups support:

* Group creation
* Group ownership
* Group administrators
* Member management
* Administrator management
* Group search
* Group deletion

Only group administrators can add or remove members, while only the group owner can delete the group or grant/revoke administrator privileges.

### Chat

The system supports two chat types:

* Private chat
* Group chat

A chat contains:

* UUID
* Participants
* Chat type
* Messages

Messages contain:

* UUID
* Sender
* Content
* Timestamp

Messages are stored inside their corresponding `Chat`, making the chat the owner of its message collection.

## Architecture

The application follows a layered **Domain – Service – Repository** architecture.

```text
Application
    │
    ▼
Service Layer
    │
    ▼
Repository Layer
    │
    ▼
In-Memory Collections
```

### Domain

Contains the main business entities:

* `Contact`
* `Group`
* `Chat`
* `Message`
* `ChatType`

### Service

Contains application and business rules:

* `ContactService`
* `GroupService`
* `ChatService`

The service layer is responsible for validation, authorization rules, coordination between entities, and interaction with repositories.

### Repository

Provides an abstraction for data access.

Current implementations include:

* `ContactArrayListRepository`
* `ContactHashMapRepository`
* `ChatRepository`
* `GroupRepository`

The contact repositories intentionally use different collection implementations to compare their behavior and performance.

## Data Structures

The project uses different Java Collections according to their intended behavior.

| Collection               | Usage                | Main Reason                          |
| ------------------------ | -------------------- | ------------------------------------ |
| `ArrayList`              | Contact repository   | Ordered collection and linear search |
| `HashMap<UUID, Contact>` | Contact repository   | Fast lookup by UUID                  |
| `HashSet<Contact>`       | Group members/admins | Prevent duplicate members            |
| `ArrayList<Message>`     | Chat messages        | Preserve message order               |

### Complexity

Searching a contact by UUID demonstrates the main difference between the two contact repository implementations:

```text
ArrayList
    Search by ID → O(n)

HashMap<UUID, Contact>
    Search by ID → O(1) average
```

However, searching by name remains `O(n)` in the current implementations because the `HashMap` is indexed by UUID, not by contact name.

This is an important distinction: **a HashMap does not automatically make every search operation O(1); the complexity depends on the key used by the index.**

## Performance Benchmark

The project contains a simple benchmark comparing `ArrayList` and `HashMap` for contact searches.

The benchmark uses:

* `System.nanoTime()`
* Multiple collection sizes
* Repeated searches
* A target contact located at the end of the `ArrayList`

Example collection sizes:

```text
100
1,000
10,000
100,000
1,000,000
```

The benchmark evaluates:

1. Search by UUID
2. Search by name

### Expected behavior

For UUID searches, the `HashMap` implementation should scale significantly better as the number of contacts increases:

```text
ArrayList → O(n)
HashMap   → O(1) average
```

For name searches, both implementations perform a linear scan:

```text
ArrayList → O(n)
HashMap   → O(n)
```

The benchmark is intended as a **learning experiment**, not as a rigorous production performance benchmark. For more precise JVM benchmarking, a framework such as JMH would be more appropriate.

## Testing

JUnit 5 is used for unit testing.

The current test suite validates the behavior of both contact repository implementations, including:

* Contact insertion
* Search by UUID
* Search by name
* Search for nonexistent contacts
* Contact deletion

Tests follow the **Arrange – Act – Assert** pattern.

Run the test suite with:

```bash
mvn test
```

## Running the Application

### Compile

```bash
mvn compile
```

### Run tests

```bash
mvn test
```

### Run the application

```bash
mvn exec:java
```

The `Application` class demonstrates the main application flows:

1. Contact registration
2. Contact search
3. Contact deletion
4. Private chat creation
5. Private message sending
6. Group creation
7. Group member management
8. Group message sending

## Technology Stack

* Java 22
* Apache Maven 3.9+
* JUnit 5
* Git
* GitHub

## Project Structure

```text
src/
├── main/
│   └── java/
│       ├── domain/
│       │   ├── Chat.java
│       │   ├── ChatType.java
│       │   ├── Contact.java
│       │   ├── Group.java
│       │   └── Message.java
│       │
│       ├── exception/
│       │   └── MyAgendaException.java
│       │
│       ├── repository/
│       │   ├── ChatRepository.java
│       │   ├── ContactArrayListRepository.java
│       │   ├── ContactHashMapRepository.java
│       │   └── GroupRepository.java
│       │
│       └── service/
│           ├── ChatService.java
│           ├── ChatServiceImpl.java
│           ├── ContactService.java
│           ├── ContactServiceImpl.java
│           ├── GroupService.java
│           └── GroupServiceImpl.java
│
└── test/
    └── java/
        └── repository/
            ├── ContactRepositoryTest.java
            └── ContactRepositoryBenchmark.java
```

## Documentation

The project includes software engineering documentation covering different stages of the system development:

* [BRD - Business Requirement Document](docs/BRD%20-%20Business%20Requirement%20Document.md)
* [PRD - Product Requirement Document](docs/PRD%20-%20Product%20Requirement%20Document.md)
* [SDD - Software Design Document](docs/SDD%20-%20Software%20Design%20Document.md)
* [TSD - Technical Specification Document](docs/TSD%20-%20Technical%20Specification%20Document.md)

The `docs/diagrams` directory contains architecture, class, interface, and sequence diagrams.

## Future Improvements

Potential next steps include:

* PostgreSQL persistence
* Spring Boot migration
* REST API
* Authentication and authorization
* Integration tests
* API documentation
* Containerization with Docker
* CI/CD pipeline
* More rigorous performance benchmarking with JMH

## Learning Objectives

This project was developed to practice:

* Java Collections
* Big-O complexity
* Object-oriented design
* Layered architecture
* Repository Pattern
* Service Layer
* Domain modeling
* Dependency Injection
* Exception handling
* Unit testing with JUnit
* Performance experimentation
* Technical documentation
* UML diagrams
* Git and Maven workflows

The main goal is not to build a production-ready messaging platform, but to use a realistic domain to understand how Java language features, data structures, architecture, testing, and software engineering practices work together.
