package core.be.dto;

import lombok.Data;

@Data
public class PetModel {

    private Long id;
    private Category category;
    private String name;
    private String[] photoUrls;
    private Tag[] tags;
    private String status;

    public PetModel() {};
}
