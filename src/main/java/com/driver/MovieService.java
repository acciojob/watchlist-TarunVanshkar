package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService
{
    @Autowired
    MovieRepository movieRepository;

    public String addMovieService(Movie movie)
    {
        return movieRepository.addMovie(movie);
    }

    public String addDirectorService(Director director)
    {
        return movieRepository.addDirector(director);
    }

    public String addMovieDirectorPairService(String movieName, String directorName)
    {
        return movieRepository.addMovieDirectorPair(movieName, directorName);
    }

    public Movie getMovieByNameService(String name)
    {
        return movieRepository.getMovieByName(name);
    }

    public Director getDirectorByNameService(String name)
    {
        return movieRepository.getDirectorByName(name);
    }

    public List<String> getMoviesByDirectorNameService(String name)
    {
        return movieRepository.getMoviesByDirectorName(name);
    }

    public List<String> findAllMoviesService()
    {
        return movieRepository.findAllMovies();
    }

    public String deleteDirectorByNameService(String name)
    {
        return movieRepository.deleteDirectorByName(name);
    }

    public String deleteAllDirectorsService()
    {
        return movieRepository.deleteAllDirectors();
    }
}
