package com.groupeisi.elearning.session.mapper;

import com.groupeisi.elearning.session.dtos.SessionRequestDto;
import com.groupeisi.elearning.session.dtos.SessionResponseDTO;
import com.groupeisi.elearning.session.entitie.SessionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    @Mapping(source = "teacherId", target = "teacher.id")
    @Mapping(source = "courseId", target = "course.id")
    SessionEntity toEntity(SessionRequestDto requestDTO);

    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "course.name", target = "courseName")
    @Mapping(source = "teacher.lastName", target = "teacherName")
//    @Mapping(source = "teacher.firstName", target = "teacherFirstName")

    SessionResponseDTO toDTO(SessionEntity session);
}