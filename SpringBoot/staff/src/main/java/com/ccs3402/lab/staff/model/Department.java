package com.ccs3402.lab.staff.model;

import java.util.Set;

import org.hibernate.annotations.Collate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private Integer deptId;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Staff> staffs;

    public Department() {
    }

    public Department(String deptName, String address, String phone) {
        this.deptName = deptName;
        this.address = address;
        this.phone = phone;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public Set<Staff> getStaffs() {
        return staffs;
    }

    @Override
    public String toString() {
        return String.format("Department[deptId=%d, deptName='%s', address='%s', phone='%s']", deptId, deptName,
                address, phone);
    }
}
