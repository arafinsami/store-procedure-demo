package com.storeprocedure.service;

import com.storeprocedure.dto.EmployeeDto;
import com.storeprocedure.utils.GenericStoreProcedure;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmployeeService {

    private final JdbcTemplate jdbcTemplate;

    private final GenericStoreProcedure procedure;


    @Transactional
    public Map<String, Object> save(EmployeeDto dto) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        to(dto, source);
        Map<String, Object> map = procedure
                .callProcedure("ADD_EMPLOYEE_PROCEDURE")
                .execute(source);
        return map;
    }

    @Transactional
    public Map<String, Object> update(EmployeeDto dto) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        update(dto, source);
        Map<String, Object> map = procedure
                .callProcedure("UPDATE_EMPLOYEE_PROCEDURE")
                .execute(source);
        return map;
    }


    public Map<String, Object> findAll() {
        SqlParameterSource source = new MapSqlParameterSource();
        Map<String, Object> map = procedure
                .callProcedure("ALL_EMPLOYEE")
                .execute(source);
        return map;
    }

    private void to(EmployeeDto dto, MapSqlParameterSource source) {
        source.addValue("name", dto.getName());
    }

    private void update(EmployeeDto dto, MapSqlParameterSource source) {
        source.addValue("id", dto.getId());
        source.addValue("name", dto.getName());
    }

    public EmployeeDto findById(Long id) {
        EmployeeDto dto = jdbcTemplate.queryForObject(
                "SELECT * FROM employee WHERE id=?",
                new BeanPropertyRowMapper<>(EmployeeDto.class),
                id
        );
        return dto;
    }

}
