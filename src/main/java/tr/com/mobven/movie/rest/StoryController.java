package tr.com.mobven.movie.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.mobven.movie.entity.Movie;
import tr.com.mobven.movie.entity.Story;
import tr.com.mobven.movie.story.StoryService;

import java.util.List;

@RestController
@RequestMapping("/story")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class StoryController {
    @Autowired
    private StoryService storyService;

    @GetMapping("/find-stories")
    ResponseEntity<Iterable<Story>> getStories() {

        final Iterable<Story> stories = storyService.getAll();

        return ResponseEntity.ok(stories);

    }

    @GetMapping("/find-stories-name")
    ResponseEntity<List<String>> getStoriesName() {

        final List<String> stories = storyService.getStoriesName();

        return ResponseEntity.ok(stories);
    }
}
