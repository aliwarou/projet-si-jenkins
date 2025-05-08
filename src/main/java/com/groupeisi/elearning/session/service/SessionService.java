package com.groupeisi.elearning.session.service;

import com.groupeisi.elearning.Exception.EntityNotFoundException;
import com.groupeisi.elearning.course.CourseEntity;
import com.groupeisi.elearning.course.repositories.CourseRepository;
import com.groupeisi.elearning.session.dtos.SessionRequestDto;
import com.groupeisi.elearning.session.dtos.SessionResponseDTO;
import com.groupeisi.elearning.session.entitie.SessionEntity;
import com.groupeisi.elearning.session.mapper.SessionMapper;
import com.groupeisi.elearning.session.repository.SessionRepository;
import com.groupeisi.elearning.teacher.TeacherEntity;
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
public class SessionService {

    private  SessionRepository sessionRepository;
    private TeacherRepository teacherRepository;
    private CourseRepository courseRepository;
    private SessionMapper sessionMapper;
    private  MessageSource messageSource;

    public static final String ENTITY_NOTFOUND = "entity.notfound";
    public static final String ENTITY_EXIST = "entity.exist";;



    public SessionResponseDTO createSession(SessionRequestDto requestDTO) {
        TeacherEntity teacher = teacherRepository.findById(requestDTO.getTeacherId())
                .orElseThrow(() -> this.entityNotFoundException("teacher", requestDTO.getTeacherId()) );

        CourseEntity course = courseRepository.findById(requestDTO.getCourseId())
                .orElseThrow(() -> this.entityNotFoundException("course",requestDTO.getCourseId()) );

        SessionEntity session = sessionMapper.toEntity(requestDTO);
        session.setTeacher(teacher);
        session.setCourse(course);

        SessionEntity savedSession = sessionRepository.save(session);
        return sessionMapper.toDTO(savedSession);
    }

    public SessionResponseDTO getSession(Long id) {
        SessionEntity session = sessionRepository.findById(id)
                .orElseThrow(() -> this.entityNotFoundException("session", id) );
        return sessionMapper.toDTO(session);
    }

    public List<SessionResponseDTO> getAllSessions() {
        return sessionRepository.findAll()
                .stream()
                .map(sessionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SessionResponseDTO updateSession(Long id, SessionRequestDto requestDTO) {
        SessionEntity existingSession = sessionRepository.findById(id)
                .orElseThrow(() -> this.entityNotFoundException("session", id) );


        TeacherEntity teacher = teacherRepository.findById(requestDTO.getTeacherId())
                .orElseThrow(() -> this.entityNotFoundException("teacher", requestDTO.getTeacherId()) );

        CourseEntity course = courseRepository.findById(requestDTO.getCourseId())
                .orElseThrow(() -> this.entityNotFoundException("course", requestDTO.getCourseId()) );


        existingSession.setName(requestDTO.getName());
        existingSession.setBeginDate(requestDTO.getBeginDate());
        existingSession.setEndDate(requestDTO.getEndDate());
        existingSession.setDescription(requestDTO.getDescription());
        existingSession.setArchive(requestDTO.isArchive());
        existingSession.setTeacher(teacher);
        existingSession.setCourse(course);

        SessionEntity savedSession = sessionRepository.save(existingSession);
        return sessionMapper.toDTO(savedSession);
    }


    public void deleteSession(Long id) {
        SessionEntity existingSession = sessionRepository.findById(id)
                .orElseThrow(() -> this.entityNotFoundException("session", id) );
        sessionRepository.deleteById(id);
    }

    private EntityNotFoundException entityNotFoundException(String name, Long id){
        return new  EntityNotFoundException(messageSource.getMessage(
                ENTITY_NOTFOUND, new Object[]{name,id},
                Locale.getDefault()
        ));

    }
}