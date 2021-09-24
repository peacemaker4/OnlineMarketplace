package org.itstep.onlinemarketplace.models;

import lombok.Data;
import org.itstep.onlinemarketplace.entities.DbUser;
import org.itstep.onlinemarketplace.entities.Tag;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Data
public class AdvertModel {

    private String title;

    private String description;

    private Integer price;

}
