package com.sample.employee.controller;

import com.sample.employee.model.Employee;
import com.sample.employee.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepo employeeRepo;

    @PostMapping()
    public void create(@RequestBody Employee employee) {
        employeeRepo.save(employee);
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable long id) {
        return employeeRepo.findOne(id);
    }

    @GetMapping()
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }
}
