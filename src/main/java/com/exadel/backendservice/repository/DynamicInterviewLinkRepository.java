package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.DynamicInterviewLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface DynamicInterviewLinkRepository extends JpaRepository<DynamicInterviewLink, UUID> {

    DynamicInterviewLink findByCode(String code);

    @Transactional
    @Modifying
    @Query(value = "delete from public.dynamic_interview_link where (CURRENT_TIMESTAMP - created_time) > CAST(:hashLifetimeLimit AS interval)", nativeQuery = true)
    void removeOldHash(String hashLifetimeLimit);

}
