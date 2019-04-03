package core.be.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import core.be.StarWarsApi;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StarWarsCharacterModel {

    private String name;
    private String height;
    private String mass;
    private String hair_color;
    private String skin_color;
    private String eye_color;
    private String birth_year;
    private String gender;
    private String homeworld;
    private String[] films;

}
