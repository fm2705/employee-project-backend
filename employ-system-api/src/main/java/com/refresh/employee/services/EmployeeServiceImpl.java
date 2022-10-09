package com.refresh.employee.services;

import com.refresh.employee.entity.EmployeeEntity;
import com.refresh.employee.model.Employee;

import com.refresh.employee.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        //copy all the values from the employee and pass into employeeEntity
        BeanUtils.copyProperties(employee, employeeEntity);
        //save the data
        employeeRepository.save(employeeEntity);
        return employee;
    }
    @Override
    public List<Employee> getAllEmployees() {
        //getting the list of employee entities from repository
        List<EmployeeEntity> employeeEntities
                = employeeRepository.findAll();
        //convert the employee entity into a list of employees using map function
        List<Employee> employees = employeeEntities
                .stream()
                .map(emp -> new Employee(
                        emp.getId(),
                        emp.getFirstName(),
                        emp.getLastName(),
                        emp.getEmailId()))
                .collect(Collectors.toList()); //collect the entire list
        return employees;
    }
}
