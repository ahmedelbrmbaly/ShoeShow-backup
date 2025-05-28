package iti.jets.model.mappers;

import iti.jets.annotation.IntegTest;
import iti.jets.model.dtos.UserAddressDTO;
import iti.jets.model.entities.UserAddress;
import iti.jets.repositories.AddressRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@IntegTest
//@Import({TestContainersConfig.class})
public class UserAddressMapperTest {

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Test
    void testToEntity() {
        // Given
        UserAddressDTO addressDto = new UserAddressDTO();
        addressDto.setBuildingNumber(123);
        addressDto.setStreet("Main St");
        addressDto.setState("NY");
        addressDto.setAddressId(1L);
        addressDto.setIsDefault(true);

        // When
        UserAddress userAddress = userAddressMapper.toEntity(addressDto);

        // Then
        assertNotNull(userAddress);
        System.out.println(addressDto.getBuildingNumber() + " " + userAddress.getStreet() + " " + userAddress.getBuildingNumber());
        assertEquals(123, userAddress.getBuildingNumber());
        assertEquals("Main St", userAddress.getStreet());
        assertEquals("NY", userAddress.getState());
        assertNotNull(userAddress.getIsDefault());
        assertTrue(userAddress.getIsDefault());
        assertEquals(1, userAddress.getAddressId());
    }

    @Test
    void testToDto() {
        // Given
        UserAddress userAddress = new UserAddress();
        userAddress.setBuildingNumber(123);
        userAddress.setStreet("Main St");
        userAddress.setState("NY");
        userAddress.setIsDefault(true);
        userAddress.setAddressId(1L);

        // When
        UserAddressDTO addressDto = userAddressMapper.toDto(userAddress);

        // Then
        assertNotNull(addressDto);
        assertEquals(123, addressDto.getBuildingNumber());
        assertEquals("Main St", addressDto.getStreet());
        assertEquals("NY", addressDto.getState());
        assertTrue(addressDto.getIsDefault());
        assertEquals(1, addressDto.getAddressId());
    }
}