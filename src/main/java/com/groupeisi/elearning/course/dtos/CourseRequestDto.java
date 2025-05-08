package com.groupeisi.elearning.course.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CourseRequestDto  {

    @NotBlank(message = "the course must have a name")
    private String name;

    private String description;

    private boolean archive;

}
