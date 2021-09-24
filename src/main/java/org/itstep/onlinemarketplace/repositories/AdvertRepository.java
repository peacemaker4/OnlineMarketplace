package org.itstep.onlinemarketplace.repositories;

import org.itstep.onlinemarketplace.entities.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long > {
}
