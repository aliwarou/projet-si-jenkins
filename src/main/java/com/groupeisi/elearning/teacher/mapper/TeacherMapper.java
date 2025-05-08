package com.groupeisi.elearning.teacher.mapper;

import com.groupeisi.elearning.teacher.TeacherEntity;
import com.groupeisi.elearning.teacher.dtos.TeacherRequestDto;
import com.groupeisi.elearning.teacher.dtos.TeacherResponseDto;
import org.mapstruct.Mapper;




    @Mapper(componentModel = "spring")
    public interface TeacherMapper {
        TeacherEntity toEntity(TeacherRequestDto teacherRequestDto);
        TeacherResponseDto toDto(TeacherEntity Teacher);
    }

