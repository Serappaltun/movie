package tr.com.serap.movie.story;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.serap.movie.entity.Story;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class StoryService {


    @Autowired
    private StoryRepository storyRepository;

    public Iterable<Story> getAll() {
        return storyRepository.findAll();
    }

    public List<String> getStoriesName() {
        final Iterable<Story> all = storyRepository.findAll();
        final List<String> stories = new ArrayList<>();
        for (Story story : all) {
            stories.add(story.getStoryName());
        }
        return stories;
    }
}
