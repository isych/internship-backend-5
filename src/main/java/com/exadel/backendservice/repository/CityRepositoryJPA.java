package com.exadel.backendservice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
@RequiredArgsConstructor
public class CityRepositoryJPA {

    private final JdbcTemplate template;

    public List<UUID> findCitiesByCountryName(String name){
        return template.query("select c.id from city c inner join country c2 on c2.id = c.country_id where c2.name = ?",
                (rs, i) -> rs.getObject("id", UUID.class),
                name);
    }

}
