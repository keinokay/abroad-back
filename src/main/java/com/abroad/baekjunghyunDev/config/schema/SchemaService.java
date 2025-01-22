package com.abroad.baekjunghyunDev.config.schema;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SchemaService {

    private final JdbcTemplate jdbcTemplate;

    public SchemaService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void changeSchema(String schemaName) {
    	System.out.println("tests");
        String sql = "USE " + schemaName;
        System.out.println(sql);
        jdbcTemplate.execute(sql);  // SQL 실행
    }
}
