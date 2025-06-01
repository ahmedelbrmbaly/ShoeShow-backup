package iti.jets.controllers.admin;

import iti.jets.model.dtos.UserManageDTO;
import iti.jets.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@Slf4j
public class AdminUserController {
    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserManageDTO>> getAllUsers() {
        log.info("Retrieving all users");
        List<UserManageDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
