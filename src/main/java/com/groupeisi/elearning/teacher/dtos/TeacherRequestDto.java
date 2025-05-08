package com.groupeisi.elearning.teacher.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class TeacherRequestDto {

    @NotBlank(message = "first is required")
    private String firstName;

    @NotBlank(message = "lastName is required")
    private String lastName;
    @Email(message = "the email format in not valid")
    private String email;

    @NotBlank(message = "phone number is required")
    private String phoneNumber;

    @NotBlank(message = "adress is required")
    private String address;

    private boolean archive;
}
