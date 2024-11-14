package com.example.employeeapp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee addEmployee(@RequestParam String name, @RequestParam int department, @RequestParam double salary) {
        if (StringUtils.isBlank(name)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "First name and last name cannot be blank.");
        }
        name = StringUtils.capitalize(name.toLowerCase());
        return employeeService.addEmployee(name, department, salary);
    }

    @DeleteMapping("/{id}")
    public boolean removeEmployee(@PathVariable int id) {
        return employeeService.removeEmployee(id);
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/total-salary")
    public double calculateTotalSalary() {
        return employeeService.calculateTotalSalary();
    }

    @GetMapping("/min-salary")
    public Optional<Employee> findEmployeeWithMinSalary() {
        return employeeService.findEmployeeWithMinSalary();
    }

    @GetMapping("/max-salary")
    public Optional<Employee> findEmployeeWithMaxSalary() {
        return employeeService.findEmployeeWithMaxSalary();
    }

    @GetMapping("/average-salary")
    public double calculateAverageSalary() {
        return employeeService.calculateAverageSalary();
    }

    @GetMapping("/names")
    public List<String> getAllEmployeeNames() {
        return employeeService.getAllEmployeeNames();
    }

    @GetMapping("/by-department/{department}")
    public List<Employee> getEmployeesByDepartment(@PathVariable int department) {
        return employeeService.getEmployeesByDepartment(department);
    }

    @GetMapping("/grouped-by-department")
    public Map<Integer, List<Employee>> groupEmployeesByDepartment() {
        return employeeService.groupEmployeesByDepartment();
    }
}
