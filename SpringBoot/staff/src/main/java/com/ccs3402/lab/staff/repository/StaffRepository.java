package com.ccs3402.lab.staff.repository;

import com.ccs3402.lab.staff.model.Staff;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

}
