package com.sample.employee.repo;

import com.sample.employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeRepo {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Employee employee) {
        String sqlQuery = "insert into employees(first_name, last_name, yearly_income) " +
                "values (?, ?, ?)";
        jdbcTemplate.update(sqlQuery,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getYearlyIncome());
    }

    public Employee findOne(long id) {
        String sqlQuery = "select id, first_name, last_name, yearly_income " +
                "from employees where id = ?";
        return jdbcTemplate.queryForObject(sqlQuery, this::mapRowToEmployee, id);
    }

    public List<Employee> findAll() {
        String sqlQuery = "select * from employees";
        return jdbcTemplate.query(sqlQuery,this::mapRowToEmployee);
    }

    private Employee mapRowToEmployee(ResultSet resultSet, int rowNum) throws SQLException {
        return Employee.builder()
                .id(resultSet.getLong("id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .yearlyIncome(resultSet.getLong("yearly_income"))
                .build();
    }

}
