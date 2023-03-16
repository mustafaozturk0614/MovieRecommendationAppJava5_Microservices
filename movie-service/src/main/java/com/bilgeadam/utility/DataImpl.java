package com.bilgeadam.utility;


import com.bilgeadam.repository.entity.Movie;
import com.bilgeadam.repository.entity.MovieComment;
import com.bilgeadam.service.GenreService;
import com.bilgeadam.service.MovieService;
import com.bilgeadam.utility.data.Sample;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataImpl  implements ApplicationRunner {

    private final MovieService movieService;

    private final GenreService genreService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
   // getAllMovies();

    }


    public  void getAllMovies(){
        try {
            URL url= new URL("https://api.tvmaze.com/shows");
            HttpURLConnection hr= (HttpURLConnection) url.openConnection();
            InputStream inputStream=hr.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String value="";
            value=bufferedReader.readLine();
            Sample[] array=new Gson().fromJson(value,Sample[].class);
             Arrays.asList(array).forEach(x->{
                 Movie movie=null;
                 if(x.network==null){
                     movie= Movie.builder()

                             .url(x.url)
                             .image(x.image.medium)
                             .language(x.language)
                             .premiered(LocalDate.parse(x.premiered))
                             .summary(x.summary)
                             .name(x.name)
                             .genres(genreService.createGenresWithNames(x.genres))
                             .rating(x.rating.average)
                             .build();
                 }else{
                     movie= Movie.builder()
                             .url(x.url)
                             .image(x.image.medium)
                             .language(x.language)
                             .premiered(LocalDate.parse(x.premiered))
                             .summary(x.summary)
                             .name(x.name)
                             .rating(x.rating.average)
                             .country(x.network.country.name)
                             .genres(genreService.createGenresWithNames(x.genres))
                             .build();
                 }
                 movieService.save(movie);
             });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



}
