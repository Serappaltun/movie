package tr.com.serap.movie.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tr.com.serap.movie.entity.Movie;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    String findAll(Model model) {

        Iterable<Movie> movies = movieService.findAll();
        model.addAttribute("movies", movies);
        Movie movie= new Movie();
        model.addAttribute("movie", movie);
        return "movies/index";
    }


    @GetMapping("/search")
    String findView(Model model, @RequestParam(value="title",required = true) final String title) {
        final Iterable<Movie> movies = movieService.findByMovieTitle(title);
        model.addAttribute("movies", movies);
        Movie movie= new Movie();
        movie.setTitle(title);
        model.addAttribute("movie", movie);
        return "movies/index";
    }

}
