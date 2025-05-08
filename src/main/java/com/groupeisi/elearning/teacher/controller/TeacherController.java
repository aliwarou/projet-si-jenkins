
package com.groupeisi.elearning.teacher.controller;

import com.groupeisi.elearning.teacher.dtos.TeacherRequestDto;
import com.groupeisi.elearning.teacher.dtos.TeacherResponseDto;
import com.groupeisi.elearning.teacher.services.TeacherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@RestController
@RequestMapping("/teachers")
public class TeacherController {

    TeacherService teacherService;



    @PostMapping
    public ResponseEntity<TeacherResponseDto> createTeacher(@Valid @RequestBody TeacherRequestDto requestDto) {
        TeacherResponseDto createdTeacher = teacherService.createTeacher(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeacher);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDto> getTeacher(@PathVariable Long id) {
        TeacherResponseDto teacher = teacherService.getTeacher(id);
        return ResponseEntity.ok(teacher);
    }


    @GetMapping
    public ResponseEntity<List<TeacherResponseDto>> getAllTeachers() {
        List<TeacherResponseDto> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponseDto> updateTeacher(@PathVariable Long id,
                                                          @Valid @RequestBody TeacherRequestDto requestDto) {
        TeacherResponseDto updatedTeacher = teacherService.updateTeacher(id, requestDto) ;
        return ResponseEntity.ok(updatedTeacher);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}