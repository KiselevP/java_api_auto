package lesson4;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Result
{
    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("image")
    private String image;

    @JsonProperty("imageType")
    private String imageType;
}
