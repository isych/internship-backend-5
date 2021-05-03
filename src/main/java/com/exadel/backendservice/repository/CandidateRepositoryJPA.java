package com.exadel.backendservice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
@RequiredArgsConstructor
public class CandidateRepositoryJPA {

    private final JdbcTemplate template;

    public List<UUID> findAllByFilter(String query) {
        return template.query(query, (rs, i) -> rs.getObject("id", UUID.class));
    }
}
