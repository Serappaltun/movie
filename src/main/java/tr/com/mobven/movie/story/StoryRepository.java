package tr.com.mobven.movie.story;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.com.mobven.movie.entity.Story;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface StoryRepository extends CrudRepository<Story, Long> {
}
