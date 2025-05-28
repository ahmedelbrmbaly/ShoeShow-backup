package iti.jets.model.mappers;

import iti.jets.model.dtos.UserDTO;
import iti.jets.model.dtos.UserManageDTO;
import iti.jets.model.dtos.UserProfileDataDTO;
import iti.jets.model.entities.User;
import iti.jets.model.mappers.custom.MapStructHelpers;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SizeMapper.class,UserAddressMapper.class, WishListMapper.class, OrderMapper.class, MapStructHelpers.class}, imports = {UserAddressMapper.class})
public interface UserMapper {

    UserManageDTO toUserManageDTO(User user);
    User toUser(UserDTO userDTO);
    UserDTO toUserDTO(User user);
    UserProfileDataDTO toUserProfileDataDTO(User user);
}
