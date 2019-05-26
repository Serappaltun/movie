package tr.com.mobven.movie.film;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long > {

   @Query("SELECT m FROM Movie m WHERE UPPER(m.title) LIKE CONCAT('%',UPPER(:title),'%')")
    Collection<Movie> findMoviesByTitle(@Param("title") String title);
}
