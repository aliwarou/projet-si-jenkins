package com.groupeisi.elearning.course.mapper;

import com.groupeisi.elearning.course.CourseEntity;
import com.groupeisi.elearning.course.dtos.CourseRequestDto;
import com.groupeisi.elearning.course.dtos.CourseResponseDto;
import org.mapstruct.Mapper;


    @Mapper(componentModel = "spring")
    public interface CourseMapper {
        CourseEntity toEntity(CourseRequestDto courseRequestDto);
        CourseResponseDto toDto(CourseEntity Course);
    }

