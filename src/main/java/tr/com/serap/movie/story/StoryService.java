package tr.com.serap.movie.story;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.serap.movie.entity.Story;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class StoryService {

    private static final String URL = "https://www.mynet.com/";

    @Autowired
    private StoryRepository storyRepository;

    public Iterable<Story> getAll() {
        return storyRepository.findAll();
    }

    public List<String> getStoriesName() {
        final List<String> stories = new ArrayList<>();
        try {
            Document document = Jsoup.connect(URL).get();
            Elements elements = document.select(".sidebar-box");
            for (Element element : elements.subList(0, Math.min(30, elements.size()))) {

                final String title = element.text();

                stories.add(title);

            }
        } catch (IOException e) {
            log.error("error");
        }
        stories.removeIf(String::isEmpty);
        return stories;
    }
}


