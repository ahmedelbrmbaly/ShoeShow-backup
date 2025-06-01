package iti.jets.controllers.user;

import iti.jets.model.dtos.UserProfileDataDTO;
import iti.jets.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for user management operations.
 */
@RestController
@RequestMapping("/api/users")
@Slf4j
@PreAuthorize("#userId == principal.user.userId")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileDataDTO> getUserById(@PathVariable Long userId) {
        log.info("Retrieving user with ID: {}", userId);
        UserProfileDataDTO user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    /**
     * Updates the profile data of a specific user.
     *
     * @param userId User ID
     * @param userProfileDataDTO User profile data
     * @return ResponseEntity with no content
     */
    @PutMapping("/{userId}")

    public ResponseEntity<Void> updateUser(@PathVariable Long userId, @RequestBody UserProfileDataDTO userProfileDataDTO) {
        log.info("Updating user with ID: {}", userId);
        userService.updateUser(userId, userProfileDataDTO);
        return ResponseEntity.noContent().build();
    }
}