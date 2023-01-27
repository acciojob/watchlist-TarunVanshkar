package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository
{
    Map<String, Movie> moviedb=new HashMap<>();
    Map<String, Director> directordb=new HashMap<>();
    Map<String, List<String>> directorMoviedb=new HashMap<>();   // director and movie pair

    public String addMovie(Movie movie)
    {
        String name=movie.getName();
        moviedb.put(name, movie);
        return "Movie added successfully";
    }

    public String addDirector(Director director)
    {
        String name=director.getName();
        directordb.put(name, director);
        return "Director added successfully";
    }

    public String addMovieDirectorPair(String movieName, String directorName)
    {
        if(moviedb.containsKey(movieName) && directordb.containsKey(directorName))
        {
            List<String> movieList=new ArrayList<>();
            if(directorMoviedb.containsKey(directorName))
            {
                movieList=directorMoviedb.get(directorName);  // To update list
            }
            movieList.add(movieName);    // add movie in list
            directorMoviedb.put(directorName, movieList);
            return "Movie Director pair added successfully";
        }
        return "Movie Director pair not found";
    }

    public Movie getMovieByName(String name)
    {
        return moviedb.get(name);
    }

    public Director getDirectorByName(String name)
    {
        return directordb.get(name);
    }

    public List<String> getMoviesByDirectorName(String name)
    {
        return directorMoviedb.get(name);
    }

    public List<String> findAllMovies()
    {
        return new ArrayList<>(moviedb.keySet());
    }

    public String deleteDirectorByName(String name)
    {
        //Delete a director and its movies from the records
        List<String> movieList=new ArrayList<>();   // to delete all movies of a particular director by name
        if(directorMoviedb.containsKey(name))
        {
            movieList=directorMoviedb.get(name);
        }

        // To delete each movie from record
        for(String movieName:movieList)
        {
            moviedb.remove(movieName);
        }

        // to delete director name
        if(directordb.containsKey(name))
        {
            directordb.remove(name);
        }
        return "Director and the corresponding movies are deleted";
    }

    public String deleteAllDirectors()
    {
        List<String> directorList=new ArrayList<>();
        List<String> movieList=new ArrayList<>();
        for(String director:directordb.keySet())
        {
            directorList.add(director);   // Fetching a list of director
        }

        for(String director:directorList)
        {
            movieList=directorMoviedb.getOrDefault(director,null);
            for(String movie:movieList)
            {
                if(movie!=null)
                {
                    moviedb.remove(movie);
                }
            }

        }


        for(String director:directorList)
        {
            directordb.remove(director);
            if(directorMoviedb.containsKey(director))
            {
                directordb.remove(director);
            }
        }
        return "Deleted successfully";
    }
}
