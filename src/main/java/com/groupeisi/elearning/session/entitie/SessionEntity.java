package com.groupeisi.elearning.session.entitie;

import com.groupeisi.elearning.course.CourseEntity;
import com.groupeisi.elearning.teacher.TeacherEntity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "sessions")
public class SessionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime beginDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean archive;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

}
