package com.groupeisi.elearning.session.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class SessionRequestDto {

    @NotBlank(message = "Session name is required")
    private String name;

    @NotNull(message = "beginDate is required")
//    @FutureOrPresent(message = "beginDate must be in the present or future")
    private LocalDateTime beginDate;

    @NotNull(message = "endDate is required")
//    @Future(message = "endDate must be in the future")
    private LocalDateTime endDate;

    private String description;

    private boolean archive;

    @NotNull(message = "teacherId is required")
    private Long teacherId;

    @NotNull(message = "courseId is required")
    private Long courseId;
}
