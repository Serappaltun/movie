package tr.com.mobven.movie.film;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tr.com.mobven.movie.entity.Movie;

import javax.transaction.Transactional;
import java.util.Collection;

@Transactional
@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m WHERE UPPER(m.title) LIKE CONCAT('%',UPPER(:title),'%')")
    Collection<Movie> findMoviesByTitle(@Param("title") String title);

    Movie findByImdbId(String s);

    //@formatter:off
    @Modifying
    @Query("UPDATE Movie SET year = :year"
         +" where imdbId = :imdbId")
    //@formatter:on
    void updateMovie(@Param("year") String year, @Param("imdbId") String imdbId);

    @Transactional
    @Modifying
    @Query( "DELETE FROM Movie m WHERE m.imdbId = :imdbID" )
    void deleteMovieByImdbId( @Param( "imdbID" ) String imdbID );
}
