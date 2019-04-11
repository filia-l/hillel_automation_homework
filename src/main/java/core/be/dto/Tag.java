package core.be.dto;

import lombok.Data;

@Data
public class Tag {

    private Integer id;
    private String name;

    public Tag(final Integer id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Tag() {};
}
