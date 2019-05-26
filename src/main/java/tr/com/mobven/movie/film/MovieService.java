package tr.com.mobven.movie.film;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@AllArgsConstructor
public class MovieService {

    @Value("${tr.com.mobven.movie.film.MovieService.omdbApiKey}")
    String API_KEY;

    @Value("${tr.com.mobven.movie.film.MovieService.hazelcastMap}")
    String HAZELCAST_MAP;

    @Value("${tr.com.mobven.movie.film.MovieService.cacheTimeInMinutes}")
    long CACHE_TIME;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private HazelcastInstance hazelcastInstance;

    Iterable<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Collection<Movie> findByMovieTitle(String title) {
        IMap<String, Collection<Movie>> map = hazelcastInstance.getMap(HAZELCAST_MAP);
        Collection<Movie> moviesByTitle = map.get(title);
        if (!CollectionUtils.isEmpty(moviesByTitle)) {
            log.info("get from hazelcast. key:" + title + "| count:" + moviesByTitle.size());
            return moviesByTitle;
        }

        moviesByTitle = movieRepository.findMoviesByTitle(title);
        if (!CollectionUtils.isEmpty(moviesByTitle)) {
            log.info("get from db. key:" + title + "| count:" + moviesByTitle.size());
            map.put(title, moviesByTitle, CACHE_TIME, TimeUnit.MINUTES);
            return moviesByTitle;
        }

        moviesByTitle = getMovieFromOmdb(title);
        if (!CollectionUtils.isEmpty(moviesByTitle)) {
            log.info("get from api. key:" + title + "| count:" + moviesByTitle.size());
            map.put(title, moviesByTitle, CACHE_TIME, TimeUnit.MINUTES);
            movieRepository.saveAll(moviesByTitle);
            return moviesByTitle;
        }


        return new ArrayList<>();

    }

    private Collection<Movie> getMovieFromOmdb(String keyword) {

        final String uri = "http://www.omdbapi.com/?apikey={apikey}&s={s}";

        final Map<String, String> params = new HashMap<>();
        params.put("s", keyword);
        params.put("apikey", API_KEY);

        try {

            final RestTemplate restTemplate = new RestTemplate();
            final ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class, params);
            final OmdbResult omdbResult = objectMapper.readValue(response.getBody(), OmdbResult.class);

            return omdbResult.getSearch();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
