package iti.jets.repositories;

import iti.jets.model.entities.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<UserAddress, Long> {
}
