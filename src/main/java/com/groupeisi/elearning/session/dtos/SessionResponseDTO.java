package com.groupeisi.elearning.session.dtos;

import lombok.Data;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Data
public class SessionResponseDTO {
    private Long id;
    private String name;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    private String description;
    private boolean archive;
    private String courseName;
    private String teacherName;
    private Long teacherId;
    private Long courseId;
}