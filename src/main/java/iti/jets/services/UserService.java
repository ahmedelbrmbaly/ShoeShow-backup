package iti.jets.services;

import iti.jets.exceptions.ResourceNotFoundException;
import iti.jets.model.dtos.ShoppingCartSummaryDTO;
import iti.jets.model.dtos.UserDTO;
import iti.jets.model.dtos.WishlistDTO;
import iti.jets.model.dtos.UserManageDTO;
import iti.jets.model.dtos.UserProfileDataDTO;
import iti.jets.model.entities.User;
import iti.jets.model.mappers.UserMapper;
import iti.jets.repositories.UserRepo;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepo userRepo;

    public UserService(UserMapper userMapper, UserRepo userRepo) {
        this.userMapper = userMapper;
        this.userRepo = userRepo;
    }

    /**
     * Helper method to find a user by ID.
     *
     * @param id User ID
     * @return User entity
     * @throws ResourceNotFoundException if user not found
     */
    private User findUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("USER_NOT_FOUND_MESSAGE" + id));
    }

    /**
     * Retrieves a list of all users.
     *
     * @return List of UserManageDTO objects
     */
    public List<UserManageDTO> getAllUsers() {
        return userRepo.findAll().stream()
                .map(userMapper::toUserManageDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a user profile by ID.
     *
     * @param id User ID
     * @return UserProfileDataDTO
     * @throws ResourceNotFoundException if user not found
     */
    public UserProfileDataDTO getUserById(Long id) {
        User user = findUserById(id);
        return userMapper.toUserProfileDataDTO(user);
    }

    /**
     * Updates a user profile.
     *
     * @param id User ID
     * @param userProfileDataDTO User profile data
     * @return Updated UserProfileDataDTO
     * @throws ResourceNotFoundException if user not found
     */
    @Transactional
    public UserProfileDataDTO updateUser(Long id, UserProfileDataDTO userProfileDataDTO) {
        User user = findUserById(id);

        // Update user fields
        user.setName(userProfileDataDTO.getName());
        user.setPhoneNumber(userProfileDataDTO.getPhoneNumber());
        user.setEmail(userProfileDataDTO.getEmail());
        user.setBirthdate(userProfileDataDTO.getBirthdate());
        user.setJob(userProfileDataDTO.getJob());
        user.setCreditLimit(userProfileDataDTO.getCreditLimit());
        user.setInterests(userProfileDataDTO.getInterests());

        // Save updated user
        User updatedUser = userRepo.save(user);

        // Return updated user profile
        return userMapper.toUserProfileDataDTO(updatedUser);
    }
}
