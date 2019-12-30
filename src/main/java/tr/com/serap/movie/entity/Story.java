package tr.com.serap.movie.entity;

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
@Table(name = "Story")
public class Story implements Serializable {

    @Id
    @JsonProperty("ID")
    private Long id;

    @JsonProperty("StoryName")
    private String storyName;

    @JsonProperty("Text")
    private String text;

}
