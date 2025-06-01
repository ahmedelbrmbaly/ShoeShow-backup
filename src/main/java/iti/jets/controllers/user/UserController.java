package iti.jets.controllers.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User Management", description = "APIs for user profile management")
@SecurityRequirement(name = "bearerAuth")

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves the profile details of a user by ID.
     *
     * @param id User ID
     * @return ResponseEntity containing a UserProfileDataDTO
     */
    @Operation(
            summary = "Get user profile by ID",
            description = "Retrieves the profile details of a user by their ID. Requires USER role."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved user profile",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserProfileDataDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Missing or invalid authentication"),
            @ApiResponse(responseCode = "403", description = "Forbidden - Not authorized to access this resource"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDataDTO> getUserById(
            @Parameter(description = "User ID", required = true, example = "1")
            @PathVariable Long id) {
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
    @Operation(
            summary = "Update user profile",
            description = "Updates the profile data of a specific user. Requires USER role."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User profile updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Missing or invalid authentication"),
            @ApiResponse(responseCode = "403", description = "Forbidden - Not authorized to access this resource"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(
            @Parameter(description = "User ID", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "User profile data to update", required = true)
            @RequestBody UserProfileDataDTO userProfileDataDTO) {
        log.info("Updating user with ID: {}", id);
        userService.updateUser(id, userProfileDataDTO);
        return ResponseEntity.noContent().build();
    }
}