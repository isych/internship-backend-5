package com.exadel.backendservice.repository;

import com.exadel.backendservice.entity.Candidate;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class CandidateRepositoryJPA {

    private final JdbcTemplate template;

    public List<Candidate> findAllByFilter(String param) {
        String query = "select * from candidate where " + param;
        List<Candidate> list = template.query(query, new BeanPropertyRowMapper<>(Candidate.class));
        return list;
    }
}
