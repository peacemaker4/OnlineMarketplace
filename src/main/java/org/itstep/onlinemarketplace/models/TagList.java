package org.itstep.onlinemarketplace.models;

import lombok.Data;
import org.itstep.onlinemarketplace.entities.Tag;

import java.util.List;

@Data
public class TagList {
    private List<Tag> tags;

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }
}
