package com.groupeisi.elearning.course;


import com.groupeisi.elearning.session.entitie.SessionEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean archive;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<SessionEntity> sessions;
}