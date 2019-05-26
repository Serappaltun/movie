package tr.com.mobven.movie.film;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@ToString
@NoArgsConstructor
@Table(name = "Movie")
public class Movie implements Serializable {

    @Id
    @JsonProperty("imdbID")
    private String imdbId;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Year")
    private String year;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Poster")
    private String poster;

}
