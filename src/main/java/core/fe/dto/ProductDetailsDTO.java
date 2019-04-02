package core.fe.dto;

import lombok.Data;

@Data
public class ProductDetailsDTO {

    private String title;
    private String color;
    private String size;
    private String qty;
    private String price;
    private String total;


    public String getTitle() {
        return title;
    }
}
