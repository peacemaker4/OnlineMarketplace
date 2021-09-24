package org.itstep.onlinemarketplace.service;

import io.swagger.models.auth.In;
import org.itstep.onlinemarketplace.entities.Advert;
import org.itstep.onlinemarketplace.entities.DbUser;
import org.itstep.onlinemarketplace.repositories.AdvertRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableWebSecurity
public class AdvertServiceImpl implements AdvertService {

    private final AdvertRepository advertRepository;

    public AdvertServiceImpl(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    @Override
    public List<Advert> getAdverts(Integer pageNO, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNO, pageSize);

        Page<Advert> pagedResult = advertRepository.findAll(paging);

        return pagedResult.getContent();
    }

    @Override
    public Advert addAdvert(Advert advert) {
        return advertRepository.save(advert);
    }
}
