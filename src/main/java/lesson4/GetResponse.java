package lesson4;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class GetResponse
{
    @JsonProperty("results")
    private List<Result> results;
    @JsonProperty("offset")
    private int offset;
    @JsonProperty("number")
    private int number;
    @JsonProperty("totalResults")
    private int totalResults;
}
