# Structural Diagrams

## Component Diagram

```
┌─────────────────────────────────────────────────────────┐
│                 Spring CRUD Application                  │
│                                                         │
│  ┌──────────────┐     ┌──────────────┐                │
│  │  Controller   │     │  Exception   │                │
│  │    Layer      │     │   Handler    │                │
│  │              │     │              │                │
│  │ UserController│     │ GlobalException│               │
│  │              │     │   Handler    │                │
│  └──────┬───────┘     └──────────────┘                │
│         │                                              │
│  ┌──────▼───────┐                                     │
│  │   Service     │     ┌──────────────┐                │
│  │    Layer      │     │     DTO      │                │
│  │              │     │    Layer     │                │
│  │  UserService  │◄───►│   UserDTO    │                │
│  └──────┬───────┘     └──────────────┘                │
│         │                                              │
│  ┌──────▼───────┐     ┌──────────────┐                │
│  │  Repository   │     │    Model     │                │
│  │    Layer      │     │    Layer     │                │
│  │              │     │              │                │
│  │UserRepository │◄───►│    User      │                │
│  └──────┬───────┘     └──────────────┘                │
│         │                                              │
└─────────┼──────────────────────────────────────────────┘
          │
  ┌───────▼───────┐
  │   MySQL 8.0   │
  │   (crud_db)   │
  │               │
  │  Table: users │
  └───────────────┘
```

## Class Diagram

```
┌───────────────────────────────┐
│       <<@Entity>>             │
│           User                │
├───────────────────────────────┤
│ - id: Long                    │
│ - username: String            │
│ - email: String               │
│ - firstName: String           │
│ - lastName: String            │
│ - address: String             │
│ - phoneNumber: String         │
│ - createdAt: LocalDateTime    │
│ - updatedAt: LocalDateTime    │
├───────────────────────────────┤
│ # onCreate(): void            │
│ # onUpdate(): void            │
└───────────────────────────────┘
           ▲
           │ uses
┌──────────┴────────────────────┐
│     <<@Repository>>           │
│      UserRepository           │
├───────────────────────────────┤
│ extends JpaRepository<User,Long>│
├───────────────────────────────┤
│ + findByUsername(String)      │
│ + findByEmail(String)         │
│ + findByFirstNameIgnoreCase() │
│ + findByLastNameIgnoreCase()  │
│ + searchByName(String)        │
│ + findByPhoneNumber(String)   │
└───────────────────────────────┘
           ▲
           │ uses
┌──────────┴────────────────────┐
│       <<@Service>>            │
│        UserService            │
├───────────────────────────────┤
│ - userRepository              │
├───────────────────────────────┤
│ + getAllUsers(): List<UserDTO> │
│ + getUserById(Long): UserDTO  │
│ + createUser(UserDTO): UserDTO│
│ + updateUser(Long,UserDTO)    │
│ + deleteUser(Long): void      │
│ + getUserByUsername(String)    │
│ + getUserByEmail(String)      │
│ + searchUsersByName(String)   │
│ - convertToDTO(User): UserDTO │
│ - convertToEntity(UserDTO)    │
└───────────────────────────────┘
           ▲
           │ uses
┌──────────┴────────────────────┐
│    <<@RestController>>        │
│       UserController          │
├───────────────────────────────┤
│ - userService                 │
├───────────────────────────────┤
│ + getAllUsers()               │
│ + getUserById(Long)           │
│ + createUser(UserDTO)         │
│ + updateUser(Long, UserDTO)   │
│ + deleteUser(Long)            │
│ + getUserByUsername(String)    │
│ + getUserByEmail(String)      │
│ + searchUsers(String)         │
└───────────────────────────────┘
```

## Package Dependency Diagram

```
com.example.controller ──────► com.example.service
        │                            │
        ▼                            ▼
com.example.dto              com.example.repository
                                     │
                                     ▼
                             com.example.model
```
