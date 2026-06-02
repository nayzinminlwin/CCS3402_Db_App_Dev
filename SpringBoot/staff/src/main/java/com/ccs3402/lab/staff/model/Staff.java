package com.ccs3402.lab.staff.model;

import jakarta.persistence.*;

@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Integer staffid;

    @Column(name = "fname")
    private String fname;

    @Column(name = "lname")
    private String lname;

    @Column(name = "salary")
    private Integer salary;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    public Staff() {
    }

    public Integer getStaffid() {
        return staffid;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public Integer getSalary() {
        return salary;
    }

    public Department getDepartment() {
        return department;
    }

    // Bean-style convenience accessors to match template property names
    public Integer getId() {
        return this.staffid;
    }

    public String getFirstName() {
        return this.fname;
    }

    public String getLastName() {
        return this.lname;
    }

    public void setStaffid(Integer staffid) {
        this.staffid = staffid;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setId(Integer id) {
        this.staffid = id;
    }

    public void setFirstName(String firstName) {
        this.fname = firstName;
    }

    public void setLastName(String lastName) {
        this.lname = lastName;
    }

    @Override
    public String toString() {
        return "Staff [staffid=" + staffid + ", fname=" + fname + ", lname=" + lname + ", salary=" + salary + "]";
    }

}
