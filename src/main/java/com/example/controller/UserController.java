package com.example.controller;

import com.example.dto.UserDTO;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * GET all users
     * curl -X GET http://localhost:8080/api/users
     */
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        log.info("GET request: Fetch all users");
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * GET user by ID
     * curl -X GET http://localhost:8080/api/users/1
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        log.info("GET request: Fetch user with id: {}", id);
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * CREATE a new user
     * curl -X POST http://localhost:8080/api/users \
     *   -H "Content-Type: application/json" \
     *   -d '{"username":"john_doe","email":"john@example.com","firstName":"John","lastName":"Doe","address":"123 Main St","phoneNumber":"1234567890"}'
     */
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        log.info("POST request: Create user with username: {}", userDTO.getUsername());
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    /**
     * UPDATE an existing user
     * curl -X PUT http://localhost:8080/api/users/1 \
     *   -H "Content-Type: application/json" \
     *   -d '{"username":"john_doe_updated","email":"john_updated@example.com","firstName":"John","lastName":"Doe","address":"456 Oak St","phoneNumber":"0987654321"}'
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserDTO userDTO) {
        log.info("PUT request: Update user with id: {}", id);
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * DELETE a user
     * curl -X DELETE http://localhost:8080/api/users/1
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        log.info("DELETE request: Delete user with id: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    /**
     * GET user by username
     * curl -X GET http://localhost:8080/api/users/search/username/john_doe
     */
    @GetMapping("/search/username/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        log.info("GET request: Fetch user with username: {}", username);
        UserDTO user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    /**
     * GET user by email
     * curl -X GET http://localhost:8080/api/users/search/email/john@example.com
     */
    @GetMapping("/search/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        log.info("GET request: Fetch user with email: {}", email);
        UserDTO user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    /**
     * SEARCH users by name
     * curl -X GET "http://localhost:8080/api/users/search?q=john"
     */
    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUsers(@RequestParam String q) {
        log.info("GET request: Search users with term: {}", q);
        List<UserDTO> users = userService.searchUsersByName(q);
        return ResponseEntity.ok(users);
    }

}
