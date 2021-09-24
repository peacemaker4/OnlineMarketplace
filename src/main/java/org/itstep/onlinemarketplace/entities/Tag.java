package org.itstep.onlinemarketplace.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
public class Tag extends BaseEntity implements GrantedAuthority {
    private String tag;

    public Tag(long id, String advert_tag, String description) {
        this.setId(id);
        this.tag = advert_tag;
    }


    @Override
    public String getAuthority() {
        return getTag();
    }
}
