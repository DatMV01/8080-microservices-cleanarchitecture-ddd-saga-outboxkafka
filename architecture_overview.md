# Architecture Overview - Food Ordering System

This document provides a high-level overview of the architectural patterns, technologies, and structural design used in the Food Ordering System.

## 🏗 Architectural Patterns

The system is designed following modern microservices principles to ensure scalability, maintainability, and reliability.

- **Clean Architecture & Hexagonal Architecture**: Separation of concerns by decoupling the core business logic (Domain) from external concerns (Frameworks, Databases, Messaging).
- **Domain-Driven Design (DDD)**: The system is structured around rich domain models, using entities, value objects, aggregates, and domain services.
- **SAGA Pattern (Orchestration)**: Manages distributed transactions across multiple microservices (Order, Payment, Restaurant) to ensure eventual consistency.
- **Transactional Outbox Pattern**: Ensures "at-least-once" delivery of events to Kafka by persisting them in the database before publishing.
- **CQRS (Command Query Responsibility Segregation)**: Separates read and write operations for optimized performance and scalability.

### Hexagonal Architecture (Detailed Flow)

```mermaid
graph TD
    subgraph "External World"
        Web[Web Browser / Mobile App]
        KCluster[Kafka Cluster]
        DB[(PostgreSQL)]
    end

    subgraph "Infrastructure Layer (Adapters)"
        Controller[REST Controller]
        KPublisher[Kafka Message Publisher]
        KListener[Kafka Message Listener]
        JpaRepo[JPA Repository Implementation]
    end

    subgraph "Application Layer (Hexagon)"
        subgraph "Input Ports"
            IService[Application Service Interface]
        end
        
        subgraph "Logic"
            CHandler[Command Handler]
            QHandler[Query Handler]
            Mapper[Data Mapper / DTOs]
        end

        subgraph "Output Ports"
            RepoPort[Repository Interface]
            MsgPort[Publisher Interface]
        end
    end

    subgraph "Domain Layer (Core)"
        Entity[Domain Entities / Aggregates]
        ValueObj[Value Objects]
        DService[Domain Services]
        DEvent[Domain Events]
    end

    %% Flow
    Web -->|JSON| Controller
    Controller -->|DTO| IService
    IService --> CHandler
    CHandler --> Mapper
    CHandler --> Entity
    Entity --> DEvent
    CHandler --> RepoPort
    CHandler --> MsgPort
    
    RepoPort -.->|Implements| JpaRepo
    MsgPort -.->|Implements| KPublisher
    KListener --> IService
    
    JpaRepo --> DB
    KPublisher --> KCluster
```
![img.png](img.png)
## 🛠 Technology Stack

- **Language**: Java 17
- **Framework**: Spring Boot 3.x
- **Database**: PostgreSQL (Relational storage for each service)
- **Messaging**: Apache Kafka (Event-driven communication)
- **Containerization**: Docker & Docker Compose
- **Build Tool**: Maven

## 📦 Microservices

The system consists of the following core services:

1.  **Customer Service**: Manages customer profiles and information.
2.  **Order Service**: The central orchestrator for the food ordering process.
3.  **Payment Service**: Handles payment processing and validation.
4.  **Restaurant Service**: Manages restaurant information and order approval logic.

### System Components & Communication

```mermaid
graph LR
    Client((Client)) -->|REST| OS[Order Service]
    OS <-->|Kafka| PS[Payment Service]
    OS <-->|Kafka| RS[Restaurant Service]
    OS -.->|Internal| CS[Customer Service]
    
    subgraph "Persistence"
        OS --- ODB[(Order DB)]
        PS --- PDB[(Payment DB)]
        RS --- RDB[(Restaurant DB)]
    end
```
![img_1.png](img_1.png)
## 📂 Internal Module Structure (Typical Service)

Each microservice is divided into several modules to enforce Hexagonal Architecture:

- **`service-domain-core`**: Contains pure domain logic (Entities, Value Objects, Domain Events). No dependencies on frameworks.
- **`service-application-service`**: Implements use cases via Command and Query handlers. Defines Input/Output ports (Interfaces).
- **`service-application`**: Contains the REST API controllers and global exception handlers.
- **`service-dataaccess`**: Implementation of repository ports for PostgreSQL using Spring Data JPA.
- **`service-messaging`**: Implementation of messaging ports for Kafka (Publishers and Listeners).
- **`service-container`**: The entry point of the application, responsible for dependency injection and configuration.

### Package Dependency Diagram

```mermaid
graph TD
    subgraph "Service Root"
        Container[service-container]
        App[service-application]
        DataAccess[service-dataaccess]
        Messaging[service-messaging]
        AppService[service-application-service]
        DomainCore[service-domain-core]
    end

    subgraph "Common"
        CommonApp[common-application]
        CommonDomain[common-domain]
    end

    Container --> App
    Container --> DataAccess
    Container --> Messaging
    
    App --> AppService
    DataAccess --> AppService
    Messaging --> AppService
    
    AppService --> DomainCore
    
    %% Common Dependencies
    AppService --> CommonApp
    DomainCore --> CommonDomain
    CommonApp --> CommonDomain
```
![img_2.png](img_2.png)
## 🏗 Common & Infrastructure

- **`common`**: Contains shared libraries used across all services:
    - `common-domain`: Shared value objects (e.g., `Money`, `OrderId`) and base classes.
    - `common-application`: Shared application-level exceptions and DTOs.
- **`infrastructure`**: Provides global infrastructure configurations:
    - `kafka`: Shared Kafka configuration and utilities.
    - `docker-compose`: YAML files for spinning up Kafka, Zookeeper, and Postgres clusters.


## 🔄 Communication Flow

1.  **Synchronous**: External clients communicate with the services via **REST APIs**.
2.  **Asynchronous**: Services communicate with each other using **Kafka Events**.
    - For example, when an order is created, `Order Service` publishes an `OrderCreatedEvent` to Kafka, which is then consumed by `Payment Service`.

## 🛡 Reliability & Consistency

- **Distributed Transactions**: Handled by the SAGA orchestrator in the `Order Service`.
- **Event Integrity**: The **Outbox Pattern** ensures that domain events are never lost, even if the messaging broker is temporarily unavailable.
- **State Management**: SAGA states (PENDING, PAID, APPROVED, CANCELLED, etc.) are tracked to manage the lifecycle of an order.

### SAGA Flow (Detailed with Rollback)

The SAGA pattern handles both the successful path and the failure path (Compensating Transactions) to ensure data consistency.

```mermaid
sequenceDiagram
    autonumber
    participant OS as Order Service
    participant PS as Payment Service
    participant RS as Restaurant Service

    Note over OS, RS: Happy Path
    OS->>PS: OrderCreatedEvent (Pending)
    PS->>PS: Validate & Deduct Funds
    PS-->>OS: OrderPaidEvent
    OS->>RS: OrderPaidEvent
    RS->>RS: Approve Order
    RS-->>OS: OrderApprovedEvent
    OS->>OS: Set Status: APPROVED

    Note over OS, RS: Compensating Transaction (Failure at Restaurant)
    OS->>PS: OrderCreatedEvent
    PS-->>OS: OrderPaidEvent
    OS->>RS: OrderPaidEvent
    RS->>RS: Out of Stock / Rejected
    RS-->>OS: OrderRejectedEvent
    OS->>OS: Set Status: CANCELLING
    OS->>PS: OrderCancelledEvent
    PS->>PS: Refund Money
    PS-->>OS: OrderRefundedEvent
    OS->>OS: Set Status: CANCELLED
```
![img_3.png](img_3.png)
