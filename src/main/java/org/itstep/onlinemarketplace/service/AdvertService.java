package org.itstep.onlinemarketplace.service;

import org.itstep.onlinemarketplace.entities.Advert;
import org.itstep.onlinemarketplace.entities.DbUser;

import java.util.List;

public interface AdvertService {

    List<Advert> getAdverts(Integer pageNO, Integer pageSize);

    Advert addAdvert(Advert advert);

}
