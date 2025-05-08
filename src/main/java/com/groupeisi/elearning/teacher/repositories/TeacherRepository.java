package com.groupeisi.elearning.teacher.repositories;

import com.groupeisi.elearning.teacher.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
    Optional<TeacherEntity> findByEmail(String email);
}
