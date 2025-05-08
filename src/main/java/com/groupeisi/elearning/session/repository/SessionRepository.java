package com.groupeisi.elearning.session.repository;



import com.groupeisi.elearning.session.entitie.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {


}
