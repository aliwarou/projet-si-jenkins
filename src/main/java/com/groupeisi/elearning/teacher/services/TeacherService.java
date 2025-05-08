
package com.groupeisi.elearning.teacher.services;


import com.groupeisi.elearning.Exception.EntityExistException;
import com.groupeisi.elearning.Exception.EntityNotFoundException;
import com.groupeisi.elearning.teacher.TeacherEntity;
import com.groupeisi.elearning.teacher.dtos.TeacherRequestDto;
import com.groupeisi.elearning.teacher.dtos.TeacherResponseDto;
import com.groupeisi.elearning.teacher.mapper.TeacherMapper;
import com.groupeisi.elearning.teacher.repositories.TeacherRepository;

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
public class TeacherService {

    private TeacherRepository teacherRepository;
    private MessageSource messageSource;
    private TeacherMapper teacherMapper;
    public static final String ENTITY_NOTFOUND = "entity.notfound";
    public static final String ENTITY_EXIST = "entity.exist";;


    public TeacherResponseDto createTeacher(TeacherRequestDto requestDto) {
        if (teacherRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new EntityExistException(messageSource.getMessage(ENTITY_EXIST, new Object[]{requestDto.getEmail()}, Locale.getDefault()));
        }
        return teacherMapper.toDto(teacherRepository.save(teacherMapper.toEntity(requestDto)));
    }

    public TeacherResponseDto getTeacher(Long id) {
        TeacherEntity teacher = teacherRepository.findById(id)
                .orElseThrow(() -> this.entityNotFoundException("teacher", id) );
        return teacherMapper.toDto(teacher);
    }

    public List<TeacherResponseDto> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(teacherMapper::toDto)
                .collect(Collectors.toList());
    }

    public TeacherResponseDto updateTeacher(Long id, TeacherRequestDto requestDto) {
        return teacherRepository.findById(id)
                .map(teacher -> {
                    teacher.setFirstName(requestDto.getFirstName());
                    teacher.setLastName(requestDto.getLastName());
                    teacher.setPhoneNumber(requestDto.getPhoneNumber());
                    teacher.setEmail(requestDto.getEmail());
                    teacher.setArchive(requestDto.isArchive());
                    return teacherMapper.toDto(teacherRepository.save(teacher));

                }).orElseThrow(() -> this.entityNotFoundException("teacher",id));
    }

    public void deleteTeacher(Long id) {
        teacherRepository.findById(id)
                .orElseThrow(() -> this.entityNotFoundException("teacher", id) );
        teacherRepository.deleteById(id);
    }

    private EntityNotFoundException entityNotFoundException(String name, Long id){
        return new  EntityNotFoundException(messageSource.getMessage(
                ENTITY_NOTFOUND, new Object[]{name,id},
                Locale.getDefault()
        ));

    }

}