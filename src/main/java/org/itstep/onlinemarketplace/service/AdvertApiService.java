package org.itstep.onlinemarketplace.service;

import org.itstep.onlinemarketplace.entities.Advert;
import reactor.core.publisher.Mono;

import java.util.List;

public interface AdvertApiService {

    List<Advert> getAdverts();

    Mono<Advert> addAdvert(Advert advert);

}
