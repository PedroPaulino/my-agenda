# SDD - Software Design Document
<strong>("How do we plan implement it ?")</strong>

**1.Overview**

MyAgenda system is a layered Java Application following Domain-Service-Repository architecture. The application is designed to allow multiple repository implementations while keeping business rules independent from the storage mechanism.

**2.System Architecture**

![alt text](/docs/diagrams/system%20architecture.jpg)

**3.Domain Model**

![alt text](/docs/diagrams/class%20diagrams.jpg)

**4.Interface Diagrams**

![alt text](/docs/diagrams/Interface%20Diagrams.jpg)

**5.Sequence Diagrams**

- *Register Contact*

![alt text](/docs/diagrams/Register%20Contact%20Sequence%20Diagram.jpg)

- *Create Group*

![alt text](/docs/diagrams/Create%20Group%20Sequence%20Diagram.jpg)

- *Add Member*

![alt text](/docs/diagrams/Add%20Member%20Sequence%20Diagram.jpg)

- *Send Message*

![alt text](/docs/diagrams/Send%20Message%20Sequence%20Diagram.jpg)

**5.Design Decisions**

- *Decision:* Repository Pattern 
- *Reason:* Allow multiple persistence implementation.
---
- *Decision:* UUID
- *Reason:* Avoid sequential IDs.
---
- *Decision:* Constructor Injection
- *Reason:* Reduce coupling and simplify testing.
---
- *Decision:* Collections
- *Reason:* Compare performance between ArrayList and HashMap
---

**6.Future Improvements**

- Database support (PostgreSQL)
- Spring Boot
- Rest API
- Authentication