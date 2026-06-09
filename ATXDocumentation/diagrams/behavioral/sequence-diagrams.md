# Behavioral Diagrams

## Sequence Diagram: Create User

```
Client          Controller       Service         Repository       Database
  │                │                │                │               │
  │ POST /users    │                │                │               │
  │───────────────►│                │                │               │
  │                │ createUser()   │                │               │
  │                │───────────────►│                │               │
  │                │                │ findByUsername()│               │
  │                │                │───────────────►│               │
  │                │                │                │ SELECT        │
  │                │                │                │──────────────►│
  │                │                │                │◄──────────────│
  │                │                │◄───────────────│ Optional.empty│
  │                │                │                │               │
  │                │                │ findByEmail()  │               │
  │                │                │───────────────►│               │
  │                │                │                │ SELECT        │
  │                │                │                │──────────────►│
  │                │                │                │◄──────────────│
  │                │                │◄───────────────│ Optional.empty│
  │                │                │                │               │
  │                │                │ save(user)     │               │
  │                │                │───────────────►│               │
  │                │                │                │ INSERT        │
  │                │                │                │──────────────►│
  │                │                │                │◄──────────────│
  │                │                │◄───────────────│ saved user    │
  │                │                │                │               │
  │                │◄───────────────│ UserDTO        │               │
  │ 201 Created   │                │                │               │
  │◄───────────────│                │                │               │
```

## Sequence Diagram: Get User by ID

```
Client          Controller       Service         Repository       Database
  │                │                │                │               │
  │ GET /users/1   │                │                │               │
  │───────────────►│                │                │               │
  │                │ getUserById(1) │                │               │
  │                │───────────────►│                │               │
  │                │                │ findById(1)    │               │
  │                │                │───────────────►│               │
  │                │                │                │ SELECT        │
  │                │                │                │──────────────►│
  │                │                │                │◄──────────────│
  │                │                │◄───────────────│ Optional<User>│
  │                │                │                │               │
  │                │◄───────────────│ UserDTO        │               │
  │ 200 OK        │                │                │               │
  │◄───────────────│                │                │               │
```

## Activity Diagram: User CRUD Lifecycle

```
          ┌─────────┐
          │  START  │
          └────┬────┘
               │
      ┌────────▼────────┐
      │  Receive HTTP   │
      │    Request      │
      └────────┬────────┘
               │
      ┌────────▼────────┐     ┌──────────────┐
      │  Bean Validation │────►│ Return 400   │
      │  (@Valid)        │fail │ + errors     │
      └────────┬────────┘     └──────────────┘
               │ pass
      ┌────────▼────────┐
      │ Route to Service│
      │   Method        │
      └────────┬────────┘
               │
      ┌────────▼────────┐     ┌──────────────┐
      │ Business Rule   │────►│ Return 400   │
      │ Validation      │fail │ + message    │
      └────────┬────────┘     └──────────────┘
               │ pass
      ┌────────▼────────┐
      │ Execute DB      │
      │ Operation       │
      └────────┬────────┘
               │
      ┌────────▼────────┐
      │ Convert to DTO  │
      │ & Return        │
      └────────┬────────┘
               │
          ┌────▼────┐
          │   END   │
          └─────────┘
```
