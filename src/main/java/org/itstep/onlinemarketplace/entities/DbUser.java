package org.itstep.onlinemarketplace.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.itstep.onlinemarketplace.entities.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DbUser extends BaseEntity {

    @Column(name = "email", unique = true)
    private String email;

    private String password;

    private String fullName;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

}
