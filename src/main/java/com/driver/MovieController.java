package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController
{
    @Autowired
    MovieService movieService;

    @PostMapping("/movies/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie)
    {
        String response=movieService.addMovieService(movie);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(" /movies/add-director")
    public ResponseEntity addDirector(@RequestBody Director director)
    {
        String response=movieService.addDirectorService(director);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movie") String movieName,@RequestParam("director") String directorName)
    {
        String response=movieService.addMovieDirectorPairService(movieName, directorName);
        if(response.equals("Movie Director pair not found"))
        {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String name)
    {
        Movie movie= movieService.getMovieByNameService(name);
        return new ResponseEntity<>(movie, HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String name)
    {
        Director director=movieService.getDirectorByNameService(name);
        return new ResponseEntity<>(director, HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String name)
    {
        List<String> movieList=new ArrayList<>();
        movieList=movieService.getMoviesByDirectorNameService(name);
        return new ResponseEntity<>(movieList, HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity findAllMovies()
    {
        List<String> movieList=new ArrayList<>();
        movieList=movieService.findAllMoviesService();
        return new ResponseEntity<>(movieList, HttpStatus.FOUND);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("name") String name)
    {
        String response=movieService.deleteDirectorByNameService(name);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity deleteAllDirectors()
    {
        String response=movieService.deleteAllDirectorsService();
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
