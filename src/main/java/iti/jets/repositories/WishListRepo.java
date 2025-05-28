package iti.jets.repositories;

import iti.jets.model.entities.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepo extends JpaRepository<Wishlist, Long> {

}
