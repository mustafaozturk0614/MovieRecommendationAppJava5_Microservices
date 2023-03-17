package com.bilgeadam.mapper;

import com.bilgeadam.dto.response.MovieResponseDto;
import com.bilgeadam.repository.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IMovieMapper {

    IMovieMapper INSTANCE= Mappers.getMapper(IMovieMapper.class);



    MovieResponseDto toMovieResponseDto(final Movie movie);
}
