package com.refresh.employee.services;

import com.refresh.employee.entity.EmployeeEntity;
import com.refresh.employee.model.Employee;

import com.refresh.employee.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
}
