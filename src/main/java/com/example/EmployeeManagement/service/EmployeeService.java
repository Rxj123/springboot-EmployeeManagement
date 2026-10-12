package com.example.EmployeeManagement.service;

import com.example.EmployeeManagement.entity.Employee;
import com.example.EmployeeManagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repo;
    EmployeeService(EmployeeRepository repo) { this.repo = repo; }

    public Employee create(Employee e) { return repo.save(e); }
    public Employee getById(Long id) { return repo.findById(id).orElseThrow(); }
    public List<Employee> getAll() { return repo.findAll(); }
    public Employee update(Long id, Employee e) {
        e.setId(id);
        return repo.save(e);
    }
    public void delete(Long id) { repo.deleteById(id); }
}