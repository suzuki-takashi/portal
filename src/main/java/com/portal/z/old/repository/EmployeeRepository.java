package com.portal.z.old.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portal.z.old.domain.employee.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
