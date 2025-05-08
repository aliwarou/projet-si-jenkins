package com.groupeisi.elearning.sessions.service;



import com.groupeisi.elearning.Exception.EntityNotFoundException;
import com.groupeisi.elearning.course.CourseEntity;
import com.groupeisi.elearning.course.repositories.CourseRepository;
import com.groupeisi.elearning.session.dtos.SessionRequestDto;
import com.groupeisi.elearning.session.dtos.SessionResponseDTO;
import com.groupeisi.elearning.session.entitie.SessionEntity;
import com.groupeisi.elearning.session.mapper.SessionMapper;
import com.groupeisi.elearning.session.repository.SessionRepository;
import com.groupeisi.elearning.session.service.SessionService;
import com.groupeisi.elearning.teacher.TeacherEntity;
import com.groupeisi.elearning.teacher.repositories.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private SessionMapper sessionMapper;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private SessionService sessionService;

    private SessionEntity session;


    @Test
    void CreateSessionOK() {
        when(teacherRepository.findById(anyLong())).thenReturn(Optional.of(getTeacher()));
        when(courseRepository.findById(anyLong())).thenReturn(Optional.of(getCourse()));
        when(sessionMapper.toEntity(any())).thenReturn(getSessionEntity());
        when(sessionRepository.save(any())).thenReturn(getSessionEntity());
        when(sessionMapper.toDTO(any())).thenReturn(getResponseDto());

        SessionResponseDTO result = sessionService.createSession(getRequestDto());

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Session 1");
        assertThat(result.getTeacherId()).isEqualTo(1L);
        assertThat(result.getCourseId()).isEqualTo(1L);
    }

    @Test
    void shouldThrowExceptionWhenTeacherNotFound() {
        when(teacherRepository.findById(getRequestDto().getTeacherId())).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("entity.notfound"), any(), any(Locale.class)))
                .thenReturn("Teacher with ID 1 not found");

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            sessionService.createSession(getRequestDto());
        });

        assertThat(exception.getMessage()).isEqualTo("Teacher with ID 1 not found");
    }

    @Test
    void shouldReturnSessionById() {
        when(sessionRepository.findById(anyLong())).thenReturn(Optional.of(getSessionEntity()));
        when(sessionMapper.toDTO(any())).thenReturn(getResponseDto());

        SessionResponseDTO session1 = sessionService.getSession(1L);

        assertThat(session1).isNotNull();
        assertThat(session1.getName()).isEqualTo("Session 1");

        verify(sessionRepository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenSessionNotFound() {
        when(sessionRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("entity.notfound"), any(), any(Locale.class)))
                .thenReturn("Session with ID 1 not found");

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            sessionService.getSession(1L);
        });

        assertThat(exception.getMessage()).isEqualTo("Session with ID 1 not found");
    }

    @Test
    void shouldReturnAllSessions() {
        when(sessionRepository.findAll()).thenReturn(List.of(getSessionEntity()));
        when(sessionMapper.toDTO(any())).thenReturn(getResponseDto());

        List<SessionResponseDTO> results = sessionService.getAllSessions();

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getName()).isEqualTo("Session 1");

        verify(sessionRepository, times(1)).findAll();
    }

    @Test
    void DeleteSessionOk() {
        when(sessionRepository.findById(anyLong())).thenReturn(Optional.of(getSessionEntity()));
        sessionService.deleteSession(1L);
        verify(sessionRepository, times(1)).deleteById(1L);
    }
    @Test
    void DeleteSessionKO() {
        when(sessionRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("entity.notfound"), any(), any(Locale.class)))
                .thenReturn("session with ID 1 not found");

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            sessionService.deleteSession(1L);
        });

        assertThat(exception.getMessage()).isEqualTo("session with ID 1 not found");

    }


    @Test
    void updateSessionOk() {
        SessionEntity sessionEntity = getSessionEntity();
        SessionRequestDto requestDto = getRequestDto();
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(sessionEntity));
        when(teacherRepository.findById(requestDto.getTeacherId())).thenReturn(Optional.of(getTeacher()));
        when(courseRepository.findById(requestDto.getCourseId())).thenReturn(Optional.of(getCourse()));
        when(sessionRepository.save(sessionEntity)).thenReturn(sessionEntity);
        when(sessionMapper.toDTO(sessionEntity)).thenReturn(getResponseDto());

        SessionResponseDTO updatedResponse = sessionService.updateSession(1L, requestDto);

        assertThat(updatedResponse).isNotNull();
        assertThat(updatedResponse.getName()).isEqualTo("Session 1");
        assertThat(updatedResponse.getBeginDate()).isEqualTo(requestDto.getBeginDate());
        assertThat(updatedResponse.getEndDate()).isEqualTo(requestDto.getEndDate());
    }

    @Test
    void updateSessionSessionNotFound() {
        when(sessionRepository.findById(1L)).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("entity.notfound"), any(), any(Locale.class)))
                .thenReturn("session with ID 1 not found");

        SessionRequestDto requestDto = getRequestDto();
        Exception exception = assertThrows(EntityNotFoundException.class, () ->
                sessionService.updateSession(1L, requestDto)
        );

        assertThat(exception.getMessage()).isEqualTo("session with ID 1 not found");
    }

    @Test
    void updateSessionTeacherNotFound() {
        SessionEntity existingSession = getSessionEntity();
        SessionRequestDto requestDto = getRequestDto();
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(existingSession));
        when(teacherRepository.findById(requestDto.getTeacherId())).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("entity.notfound"), any(), any(Locale.class)))
                .thenReturn("teacher with ID 1 not found");

        Exception exception = assertThrows(EntityNotFoundException.class, () ->
                sessionService.updateSession(1L, requestDto)
        );

        assertThat(exception.getMessage()).isEqualTo("teacher with ID 1 not found");
    }

    @Test
    void updateSessionCourseNotFound() {
    SessionEntity sessionEntity = getSessionEntity();
    SessionRequestDto requestDto = getRequestDto();
    when(sessionRepository.findById(1L)).thenReturn(Optional.of(sessionEntity));
    when(teacherRepository.findById(requestDto.getTeacherId())).thenReturn(Optional.of(getTeacher()));
    when(courseRepository.findById(requestDto.getCourseId())).thenReturn(Optional.empty());
    when(messageSource.getMessage(eq("entity.notfound"), any(), any(Locale.class)))
            .thenReturn("course with ID 1 not found");

    Exception exception = assertThrows(EntityNotFoundException.class, () ->
            sessionService.updateSession(1L, requestDto)
    );

    assertThat(exception.getMessage()).isEqualTo("course with ID 1 not found");
}

    private TeacherEntity getTeacher(){
        TeacherEntity teacher = new TeacherEntity();
        teacher.setId(1L);
        teacher.setEmail("ali@email.com");
        teacher.setLastName("Warou");
        teacher.setFirstName("Ali");
        return  teacher;
    }

    private CourseEntity getCourse(){
        CourseEntity course = new CourseEntity();
        course.setId(1L);
        course.setArchive(false);
        course.setName("cours de JEE");
        return  course;
    }

    private SessionEntity getSessionEntity(){
        SessionEntity session = new SessionEntity();
        session.setId(1L);
        session.setName("Session 1");
        session.setTeacher(this.getTeacher());
        session.setCourse(this.getCourse());
        session.setBeginDate(LocalDateTime.of(2025, 2, 1,8,30));
        session.setEndDate(LocalDateTime.of(2025, 2, 1,8,30));
        session.setDescription("Formation spring boot");
        session.setArchive(false);
        return  session;
    }

    private SessionRequestDto getRequestDto(){
        SessionRequestDto requestDto = new SessionRequestDto();
        requestDto.setName("Session 1");
        requestDto.setTeacherId(1L);
        requestDto.setCourseId(1L);
        requestDto.setBeginDate(LocalDateTime.of(2025, 2, 1,8,30));
        requestDto.setEndDate(LocalDateTime.of(2025, 2, 1,10,30));
        requestDto.setDescription("Formation spring boot");
        requestDto.setArchive(false);
        return requestDto;
    }

    private SessionResponseDTO getResponseDto(){
        SessionResponseDTO responseDto = new SessionResponseDTO();
        responseDto.setId(1L);
        responseDto.setName("Session 1");
        responseDto.setBeginDate(LocalDateTime.of(2025, 2, 1,8,30));
        responseDto.setEndDate(LocalDateTime.of(2025, 2, 1,10,30));
        responseDto.setTeacherId(1L);
        responseDto.setCourseId(1L);
        responseDto.setArchive(false);
        return responseDto;
    }

}