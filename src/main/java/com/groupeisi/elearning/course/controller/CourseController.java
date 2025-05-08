package com.groupeisi.elearning.course.controller;

import com.groupeisi.elearning.course.dtos.CourseRequestDto;
import com.groupeisi.elearning.course.dtos.CourseResponseDto;
import com.groupeisi.elearning.course.services.CourseService;
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
@RequestMapping("/courses")
public class CourseController {

     CourseService courseService;



    @PostMapping
    public ResponseEntity<CourseResponseDto> createCourse(@Valid @RequestBody CourseRequestDto requestDto) {
        CourseResponseDto createdCourse = courseService.createCourse(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDto> getCourse(@PathVariable Long id) {
        CourseResponseDto course = courseService.getCourse(id);
        return ResponseEntity.ok(course);
    }


    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getAllCourses() {
        List<CourseResponseDto> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDto> updateCourse(@PathVariable Long id,
                                                            @Valid @RequestBody CourseRequestDto requestDto) {
        CourseResponseDto updatedCourse = courseService.updateCourse(id, requestDto) ;
        return ResponseEntity.ok(updatedCourse);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}