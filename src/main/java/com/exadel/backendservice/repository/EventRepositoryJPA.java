package com.exadel.backendservice.repository;

import com.exadel.backendservice.dto.resp.EventsFilterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class EventRepositoryJPA {

    private final JdbcTemplate template;

    public List<EventsFilterDto> findAllByFilter(String query) {
        return template.query(query, new BeanPropertyRowMapper<>(EventsFilterDto.class));

    }
}
