# TSD - Technical Specification Document
<strong>("Which technologies and decisions will be used ?")</strong>

**1.Technology Stack**

Language:
- Java 22+

Build Tool:
- Apache Maven 3.9+

Testing:
- JUnit 5

Version Control:
- Git
- GitHub

**2.Development Environment**

JDK:
- OpenJDK 22

Build:
- Maven

Execution:

```
mvn compile
```

```
mvn test
```

```
mvn exec:java
```

**3.Build Configuration**

- Maven is used to manage the project compilation, testing and documentation.
- Maven Compiler Plugin is used to compile Java 22
- Maven Surefire Plugin is used to run JUnit tests in an isolated classloader.
- Maven Exec Plugin is used to execute a Java program in this case Application Class

**4.Data Structure**

4.1 ArrayList

- Used to implement one of the contact repositories.
- Dynamic array as we can have a user with no contacts at first and growing as it goes.
- Preserves the insertion order of contacts.

4.2 HashMap

- Used to implement one of the contact repositories.
- Stores contacts using their ID as the key.
- Fast average performance O(1) for insert, search and delete of contacts.
- It is used to compare lookup performance against the ArrayList implementation.


4.3 Set

- Used to store group members and administrators without allowing duplicate contacts.
- A set also ensures that the same contact cannot be added multiple times to the same collection.
- It is also used to represent unique group memberships.


4.4 ArrayList<Message>

- Used to store the messages belonging to a chat.
- Preserves the insertion order of messages.
- Allows messages to be accesed by index.
- Supports adding and removing messages dynamically.
- The collection can be traversed sequentially when displaying the chat history.

**5.Algorithms**

5.1 Contact Search by ID

ArrayList:
Linear search through the collection comparing each contact UUID.
Time complexity: O(n).

HashMap:
Key-based lookup using the contact UUID.
Average time complexity: O(1)

5.2 Contact Search by Name

ArrayList:
Linear search through the collection comparing each contact Name.
Time complexity: O(n).

5.3 Group Membership Validation

HashSet:
Checks whether the contact exists in the group's member collection.
Average time complexity: O(1)

5.4 Message Retrieval

ArrayList<Message>:
Direct access by index.
Time complexity: O(1)

5.5 Message Search by ID:

ArrayList:
Linear search through the collection.
Time complexity: O(n).

**6.Testing Strategy**

This project will use unit testing as primary testing strategy.

JUnit 5 will be used to validate business rules and the behavior of individual components.

6.1 Service Tests

- Happy Path
- Invalid Inputs
- Business rules violations

6.2 Repository Tests

ContactArrayListRepository and ContactHashMapRepository will be tested.

- Contact creation
- Contact search by ID
- Contact search by name
- Contact deletion

6.3 Test Structure

Arrange-Act-Assert pattern.

6.4 Test Execution

mvn test