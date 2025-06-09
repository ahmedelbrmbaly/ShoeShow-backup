package iti.jets.services;

import iti.jets.exceptions.ResourceNotFoundException;
import iti.jets.model.dtos.*;
import iti.jets.model.entities.Product;
import iti.jets.model.entities.User;
import iti.jets.model.entities.UserAddress;
import iti.jets.model.mappers.UserAddressMapper;
import iti.jets.model.mappers.UserMapper;
import iti.jets.repositories.UserRepo;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    private final UserMapper userMapper;
    private final UserRepo userRepo;
    private final UserAddressMapper userAddressMapper;

    public UserService(UserMapper userMapper, UserRepo userRepo, UserAddressMapper userAddressMapper) {
        this.userMapper = userMapper;
        this.userRepo = userRepo;
        this.userAddressMapper = userAddressMapper;
    }

    /**
     * Helper method to find a user by ID.
     *
     * @param id User ID
     * @return User entity
     * @throws ResourceNotFoundException if user not found
     */
    private User findUserById(Long id) {
        return userRepo.findByUserId(id)
                .orElseThrow(() -> new ResourceNotFoundException("USER_NOT_FOUND_MESSAGE" + id));
    }

    /**
     * Retrieves a list of all users.
     *
     * @return List of UserManageDTO objects
     */
    public Page<UserManageDTO> getAllUsers(String searchKeyword, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);

        Page<User> users = userRepo.findByNameContainingIgnoreCase(searchKeyword, pageable);
        log.info("Retrieved {} users with search keyword '{}'", users.getTotalElements(), searchKeyword);
        return users.map(userMapper::toUserManageDTO);
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
        log.info("Updating user with ID: {}", id);
        log.info("User profile data to update: {}", userProfileDataDTO);
        User user = findUserById(id);

        // Update user fields
        user.setName(userProfileDataDTO.getName());
        user.setPhoneNumber(userProfileDataDTO.getPhoneNumber());
        user.setEmail(userProfileDataDTO.getEmail());
        user.setBirthdate(userProfileDataDTO.getBirthdate());
        user.setJob(userProfileDataDTO.getJob());
        user.setCreditLimit(userProfileDataDTO.getCreditLimit());
        user.setInterests(userProfileDataDTO.getInterests());

        if (userProfileDataDTO.getAddresses() != null) {
            user.getAddresses().clear();

            for (UserAddressDTO addressDTO : userProfileDataDTO.getAddresses()) {
                UserAddress address = userAddressMapper.toEntity(addressDTO);
                address.setUser(user);
                user.getAddresses().add(address);
            }
        }


        // Save updated user
        User updatedUser = userRepo.save(user);

        // Return updated user profile
        return userMapper.toUserProfileDataDTO(updatedUser);
    }
}
