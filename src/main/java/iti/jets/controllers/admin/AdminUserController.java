package iti.jets.controllers.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import iti.jets.model.dtos.UserManageDTO;
import iti.jets.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@Slf4j
@Tag(name = "Admin User Management", description = "APIs for admin user management operations")
@SecurityRequirement(name = "bearerAuth")

public class AdminUserController {
    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Get all users",
            description = "Retrieves a list of all users in the system. Requires ADMIN role."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved all users",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserManageDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Missing or invalid authentication"),
            @ApiResponse(responseCode = "403", description = "Forbidden - Not authorized to access this resource")
    })
    @GetMapping
    public ResponseEntity<Page<UserManageDTO>> getAllUsers(
            @Parameter(description = "search keyword") @RequestParam(value = "searchKeyword", defaultValue = "") String searchKeyword,
            @Parameter(description = "page number") @RequestParam(value = "page", defaultValue = "0")int page,
            @Parameter(description = "page size") @RequestParam(value = "size", defaultValue = "10")int size)
     {
        log.info("Retrieving all users");
        Page<UserManageDTO> users = userService.getAllUsers(searchKeyword , page , size);
//         log.info("Got {} Users", users.getNumberOfElements());

         return ResponseEntity.ok(users);
    }
}
