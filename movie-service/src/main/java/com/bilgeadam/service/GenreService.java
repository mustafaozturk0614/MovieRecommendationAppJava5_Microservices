package com.bilgeadam.service;

import com.bilgeadam.repository.IGenreRepository;
import com.bilgeadam.repository.IMovieRepository;
import com.bilgeadam.repository.entity.Genre;
import com.bilgeadam.repository.entity.Movie;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService extends ServiceManager<Genre,String> {

    private final IGenreRepository genreRepository;

    public GenreService(IGenreRepository genreRepository) {
        super(genreRepository);
        this.genreRepository = genreRepository;
    }

    public List<Genre> createGenresWithNames(List<String> genres) {

        List<Genre> genresList=new ArrayList<>();

        for (String name:genres){
            Optional<Genre> genre=genreRepository.findOptionalByName(name);
            if(genre.isPresent()){
                genresList.add(genre.get()); //databasedeki nesneyi ekleyecegiz
            }else{
                Genre genre1= Genre.builder().name(name).build();
                genreRepository.save(genre1);
                //   genresList.add(genreRepository.save(Genre.builder().name(name).build()));
                genresList.add(genre1); //yeni bir genre ollustrup onu ekleyecegiz
            }
        }


        return  genresList;
    }


   public List<Genre> getGenresByIds(List<String> genreIds){
        return genreRepository.findAllByIdIn(genreIds);
   }
}
