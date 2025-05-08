package com.groupeisi.elearning.course.dtos;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDto implements Serializable {

    private Long id;
    private String name;
    private String description;
    private boolean archive;
}
