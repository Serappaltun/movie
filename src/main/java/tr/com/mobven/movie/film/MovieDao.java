package tr.com.mobven.movie.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.stereotype.Repository;
import tr.com.mobven.movie.common.AbstractDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@Repository
@Transactional
public class MovieDao extends AbstractDao
{
	
	public MovieDao(EntityManager entityManager, EntityManagerFactory entityManagerFactory )
	{
		super( entityManager, entityManagerFactory );
	}
	
	//@formatter:off
	@Query( "SELECT NEW tr.com.thy.othello.web_3_0.movie.MovieDto( title, year) "
			+ " FROM Movie mov"
			+ " WHERE mov.type = :movieType" )
	//@formatter:on
	public Collection<MovieDto> findMovieByMovieType(String movieType )
	{
		//@formatter:off
		final TypedQuery< MovieDto > query = getQuery( "findMovieByMovieType", MovieDto.class )
				.setParameter( "movieType", movieType );
		//@formatter:on
		
		try
		{
			return query.getResultList();
		}
		catch ( Exception e )
		{
			return new ArrayList<>();
		}
	}
}