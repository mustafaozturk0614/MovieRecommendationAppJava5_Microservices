package com.bilgeadam.mapper;

import com.bilgeadam.dto.response.MovieResponseDto;
import com.bilgeadam.repository.entity.Movie;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-17T12:35:11+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.5 (JetBrains s.r.o.)"
)
@Component
public class IMovieMapperImpl implements IMovieMapper {

    @Override
    public MovieResponseDto toMovieResponseDto(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MovieResponseDto.MovieResponseDtoBuilder movieResponseDto = MovieResponseDto.builder();

        movieResponseDto.id( movie.getId() );
        movieResponseDto.language( movie.getLanguage() );
        movieResponseDto.image( movie.getImage() );
        movieResponseDto.name( movie.getName() );
        movieResponseDto.country( movie.getCountry() );
        movieResponseDto.rating( movie.getRating() );
        movieResponseDto.summary( movie.getSummary() );
        movieResponseDto.premiered( movie.getPremiered() );

        return movieResponseDto.build();
    }
}
