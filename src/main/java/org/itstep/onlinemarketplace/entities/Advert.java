package org.itstep.onlinemarketplace.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Integer price;

//    private String cover;
//
//    private String coverHash;

//    @ManyToOne(fetch = FetchType.EAGER)
//    private DbUser user;
//
    private Long user_id;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Tag> tag;

    public Advert(String title, String description, Integer price, Long user_id) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.user_id = user_id;
//        this.coverHash = null;
//        this.cover = null;
        this.tag = null;
    }
}
