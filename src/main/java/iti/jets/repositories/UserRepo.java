package iti.jets.repositories;

import iti.jets.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUserId(Long userId);

    @Query("SELECT u.creditLimit FROM User u WHERE u.userId = :userId")
    BigDecimal findCreditLimitByUserId(@Param("userId") Long userId);
}
