# Advanced Java Design Pattern Project

This project demonstrates the implementation of various design patterns in Java. The primary goal is to showcase the correct use of design patterns such as Composite, Visitor, Observer, and Adapter. The project includes comprehensive JUnit tests to ensure the functionality and correctness of the implemented patterns.

## Design Patterns Used

### Composite Pattern
The Composite pattern is used to treat individual objects and compositions of objects uniformly. This pattern is particularly useful when dealing with tree structures or hierarchies.

**Example:**
- **Class: `Community` (not provided)**
  - The `Community` class serves as a composite element, allowing `Chat` objects to be treated as individual elements or part of a larger community structure.

### Visitor Pattern
The Visitor pattern allows you to define operations on elements of an object structure without changing the classes of the elements. It is useful for performing various operations across a collection of objects with different types.

**Example:**
- **Class: `ChatAddedUserEvent`**
  - The `accept` method in `ChatAddedUserEvent` allows a `ChatEventVisitor` to perform operations on the event.
    ```java
    @Override
    public void accept(ChatEventVisitor visitor) {
        visitor.visitAddedUserEvent(this);
    }
    ```
- **Class: `ChatRemovedUserEvent`**
  - Similarly, the `accept` method in `ChatRemovedUserEvent` allows a `ChatEventVisitor` to perform operations on the event.
    ```java
    @Override
    public void accept(ChatEventVisitor visitor) {
        visitor.visitRemoveUserEvent(this);
    }
    ```

### Observer Pattern
The Observer pattern is used to notify multiple objects about state changes. It is particularly useful for implementing distributed event-handling systems.

**Example:**
- **Class: `Chat`**
  - The `Chat` class maintains a collection of `ChatObserver` objects and notifies them of events such as adding or removing a user.
    ```java
    public boolean addUser(ChatObserver user) {
        boolean completed = users.add(user);
        if (completed) {
            notifyObservers(new ChatAddedUserEvent(user));
        }
        return completed;
    }

    public boolean removeUser(ChatObserver user) {
        boolean completed = users.remove(user);
        if (completed) {
            notifyObservers(new ChatRemovedUserEvent(user));
        }
        return completed;
    }

    protected void notifyObservers(ChatEvent event) {
        users.stream().forEach(user -> user.update(event));
    }
    ```
- **Interface: `ChatObserver`**
  - The `ChatObserver` interface defines methods for sending messages, updating based on events, joining chats, and exiting chats.
    ```java
    public interface ChatObserver {
        public Message sendMessage(Community chat, String text);
        public void update(ChatEvent e);
        public boolean joinChat(Chat chat);
        public boolean exitChat(Chat chat);
    }
    ```

### Adapter Pattern
The Adapter pattern allows incompatible interfaces to work together. It acts as a bridge between two incompatible interfaces.

**Example:**
- **Class: `ChatUserEvent` (not provided)**
  - This class serves as an adapter to unify different types of user events (e.g., adding or removing a user) under a common interface.

## Code Functionality

The main functionality of the code revolves around managing a chat system where users can join or leave chats, and events are triggered to notify other users about these actions.

### Key Classes and Interfaces
- **Chat**
  - An abstract class that represents a chat. It maintains a list of users and notifies them of events such as adding or removing a user.
- **ChatObserver**
  - An interface that defines methods for chat observers, including sending messages, updating based on events, joining chats, and exiting chats.
- **ChatAddedUserEvent**
  - A class representing the event of adding a user to a chat. It implements the `accept` method for the Visitor pattern.
- **ChatRemovedUserEvent**
  - A class representing the event of removing a user from a chat. It implements the `accept` method for the Visitor pattern.

## JUnit Tests

The project includes JUnit tests to verify the functionality and correctness of the implemented design patterns. The tests cover various scenarios such as adding and removing users from a chat, and ensuring that observers are notified correctly.

### Example Test Class
- **Class: `ChatTests` (not retrieved)**
  - This class contains tests for the `Chat` class, verifying that users can be added and removed, and that observers are notified of these events.

## How to Run the Project

1. Clone the repository to your local machine.
2. Open the project in your preferred Java IDE (e.g., Eclipse, IntelliJ IDEA).
3. Ensure that the required libraries (e.g., JUnit) are included in your project's build path.
4. Run the JUnit test classes to verify the functionality of the code.
5. Explore the classes to understand the implementation of the design patterns.
