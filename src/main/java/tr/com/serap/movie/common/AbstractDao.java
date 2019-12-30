package tr.com.serap.movie.common;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.lang.reflect.Method;


@AllArgsConstructor
public abstract class AbstractDao
{
	
	protected final EntityManager entityManager;
	
	private final EntityManagerFactory entityManagerFactory;
	
	private final String prefix = getClass().getSimpleName() + "#";
	
	@PostConstruct
	private void createNamedQueries()
	{
		for ( Method method : getClass().getDeclaredMethods() )
		{
			if ( method.isAnnotationPresent( Query.class ) )
			{
				final Query queryAnnotation = method.getAnnotation( Query.class );
				final String queryString = queryAnnotation.value();
				
				javax.persistence.Query query;
				if ( queryAnnotation.nativeQuery() )
				{
					query = entityManager.createNativeQuery( queryString );
				}
				else
				{
					query = entityManager.createQuery( queryString );
				}
				entityManagerFactory.addNamedQuery( prefix + method.getName(), query );
			}
		}
	}
	
	protected javax.persistence.Query getQuery( String methodName )
	{
		return entityManager.createNamedQuery( prefix + methodName );
	}
	
	protected < T > javax.persistence.TypedQuery< T > getQuery( String methodName, Class< T > clazz )
	{
		return entityManager.createNamedQuery( prefix + methodName, clazz );
	}
	
	protected < T > javax.persistence.Query getWithNativeQuery( String query )
	{
		return entityManager.createNativeQuery( query );
	}
	
}