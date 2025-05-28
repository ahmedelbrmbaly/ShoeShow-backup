package iti.jets.model.mappers;

import iti.jets.model.dtos.UserAddressDTO;
import iti.jets.model.entities.UserAddress;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAddressMapper {

    UserAddress toEntity(UserAddressDTO addressDto);
    UserAddressDTO toDto(UserAddress userAddressEntity);
}