package iti.jets.services;

import iti.jets.model.dtos.ShoppingCartSummaryDTO;
import iti.jets.model.dtos.UserDTO;
import iti.jets.model.dtos.WishlistDTO;
import iti.jets.model.dtos.UserManageDTO;
import iti.jets.model.entities.User;
import iti.jets.model.mappers.UserMapper;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

}
