package tr.com.serap.movie.film;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tr.com.serap.movie.entity.Movie;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class OmdbResult implements Serializable {

    @JsonProperty("Search")
    private List<Movie> search;

    private String totalResults;

    @JsonProperty("Response")
    private String response;
}
