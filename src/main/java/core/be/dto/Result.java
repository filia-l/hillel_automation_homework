package core.be.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    private String headline;
    private String body;

    public Result() { }

    public Result(String headline, String body) {
        this.headline = headline;
        this.body = body;
    }

    public void setBody(String body) {
        this.body = body.replaceAll("( )+", " ").replaceAll("\\n", " ");
    }
}
