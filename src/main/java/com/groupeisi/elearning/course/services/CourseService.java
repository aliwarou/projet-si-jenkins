package com.groupeisi.elearning.course.services;

import com.groupeisi.elearning.Exception.EntityExistException;
import com.groupeisi.elearning.Exception.EntityNotFoundException;
import com.groupeisi.elearning.course.CourseEntity;
import com.groupeisi.elearning.course.dtos.CourseRequestDto;
import com.groupeisi.elearning.course.dtos.CourseResponseDto;
import com.groupeisi.elearning.course.mapper.CourseMapper;
import com.groupeisi.elearning.course.repositories.CourseRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Getter
@Setter
@Transactional
public class CourseService {

    private CourseRepository courseRepository;
    private MessageSource messageSource;
    private CourseMapper courseMapper;

    public static final String ENTITY_NOTFOUND = "entity.notfound";
    public static final String ENTITY_EXIST = "entity.exist";;


    public CourseResponseDto createCourse(CourseRequestDto requestDto) {
        if (courseRepository.findByName(requestDto.getName()).isPresent()) {
            throw new EntityExistException(messageSource.getMessage(ENTITY_EXIST, new Object[]{requestDto.getName()}, Locale.getDefault()));
        }
        return courseMapper.toDto(courseRepository.save(courseMapper.toEntity(requestDto)));
    }

    public CourseResponseDto getCourse(Long id) {
        CourseEntity course = courseRepository.findById(id)
                .orElseThrow(() -> this.entityNotFoundException("course", id) );
        return courseMapper.toDto(course);
    }

    public List<CourseResponseDto> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDto)
                .collect(Collectors.toList());
    }

    public CourseResponseDto updateCourse(Long id, CourseRequestDto requestDto) {
        return courseRepository.findById(id)
                .map(course -> {
                    course.setName(requestDto.getName());
                    course.setDescription(requestDto.getDescription());
                    return courseMapper.toDto(courseRepository.save(course));
                }).orElseThrow(() -> this.entityNotFoundException("course",id));
    }

    public void deleteCourse(Long id) {
        courseRepository.findById(id)
                .orElseThrow(() -> this.entityNotFoundException("course", id) );
        courseRepository.deleteById(id);
    }

    private EntityNotFoundException entityNotFoundException(String name, Long id){
        return new  EntityNotFoundException(messageSource.getMessage(
                ENTITY_NOTFOUND, new Object[]{name,id},
                Locale.getDefault()
        ));

    }

}