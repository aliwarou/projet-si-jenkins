package com.gestionecole.Etudiant_ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class Controller {
    @Autowired
    private DataSource dataSource;

    @GetMapping("/find")
    public List<Map<String,Object>> findUser(@RequestParam String email) throws SQLException {
        // ❌ VULNÉRABILITÉ : concaténation de l’input dans le SQL
        String sql = "SELECT * FROM users WHERE email = '" + email + "'";
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            List<Map<String,Object>> result = new ArrayList<>();
            while (rs.next()) {
                Map<String,Object> row = new HashMap<>();
                row.put("id", rs.getInt("id"));
                row.put("email", rs.getString("email"));
                result.add(row);
            }
            return result;
        }
    }
}
