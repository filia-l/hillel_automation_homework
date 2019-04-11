package core.be.dto;

import lombok.Data;

@Data
public class Category {

    private Integer id;
    private String name;

    public Category(final Integer id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {};
}
