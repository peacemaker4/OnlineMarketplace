package org.itstep.onlinemarketplace.repositories;

import org.itstep.onlinemarketplace.entities.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long > {
    Basket findBasketByAdvertId(Long id);
}
