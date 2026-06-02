package com.ccs3402.lab.staff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccs3402.lab.staff.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}