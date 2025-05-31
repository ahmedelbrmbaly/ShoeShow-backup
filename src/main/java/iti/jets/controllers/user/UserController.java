package iti.jets.controllers.user;

import iti.jets.model.dtos.UserProfileDataDTO;
import iti.jets.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for user management operations.
 */
@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves a list of all users.
     *
     * @return ResponseEntity containing a list of UserManageDTO objects
     */


    /**
     * Retrieves the profile details of a user by ID.
     *
     * @param id User ID
     * @return ResponseEntity containing a UserProfileDataDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDataDTO> getUserById(@PathVariable Long id) {
        log.info("Retrieving user with ID: {}", id);
        UserProfileDataDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Updates the profile data of a specific user.
     *
     * @param id User ID
     * @param userProfileDataDTO User profile data
     * @return ResponseEntity with no content
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserProfileDataDTO userProfileDataDTO) {
        log.info("Updating user with ID: {}", id);
        userService.updateUser(id, userProfileDataDTO);
        return ResponseEntity.noContent().build();
    }
}