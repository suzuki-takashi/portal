package com.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portal.domain.employee.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
