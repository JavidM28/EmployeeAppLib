package com.example.employeeapp;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();

    public Employee addEmployee(String name, int department, double salary) {
        Employee employee = new Employee(name, department, salary);
        employees.add(employee);
        return employee;
    }

    public boolean removeEmployee(int id) {
        return employees.removeIf(employee -> employee.getId() == id);
    }

    public Optional<Employee> getEmployeeById(int id) {
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst();
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    public double calculateTotalSalary() {
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    public Optional<Employee> findEmployeeWithMinSalary() {
        return employees.stream()
                .min(Comparator.comparingDouble(Employee::getSalary));
    }

    public Optional<Employee> findEmployeeWithMaxSalary() {
        return employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));
    }

    public double calculateAverageSalary() {
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);
    }

    public List<String> getAllEmployeeNames() {
        return employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
    }

    public void indexSalaries(double percent) {
        employees.forEach(employee -> employee.setSalary(employee.getSalary() * (1 + percent / 100)));
    }

    public Optional<Employee> findEmployeeWithMinSalaryByDepartment(int department) {
        return employees.stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary));
    }

    public Optional<Employee> findEmployeeWithMaxSalaryByDepartment(int department) {
        return employees.stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary));
    }

    public double calculateTotalSalaryByDepartment(int department) {
        return employees.stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    public double calculateAverageSalaryByDepartment(int department) {
        return employees.stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);
    }

    public void indexSalariesByDepartment(int department, double percent) {
        employees.stream()
                .filter(employee -> employee.getDepartment() == department)
                .forEach(employee -> employee.setSalary(employee.getSalary() * (1 + percent / 100)));
    }

    public List<Employee> getEmployeesByDepartment(int department) {
        return employees.stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> groupEmployeesByDepartment() {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
