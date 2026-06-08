package com.example.repository;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find user by username
     */
    Optional<User> findByUsername(String username);

    /**
     * Find user by email
     */
    Optional<User> findByEmail(String email);

    /**
     * Find users by first name (case-insensitive)
     */
    List<User> findByFirstNameIgnoreCase(String firstName);

    /**
     * Find users by last name (case-insensitive)
     */
    List<User> findByLastNameIgnoreCase(String lastName);

    /**
     * Custom query to find users by partial name match
     */
    @Query("SELECT u FROM User u WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<User> searchByName(@Param("searchTerm") String searchTerm);

    /**
     * Find users by phone number
     */
    Optional<User> findByPhoneNumber(String phoneNumber);

}
