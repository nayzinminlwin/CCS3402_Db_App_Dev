package com.ccs3402.lab.staff.model;

import jakarta.persistence.*;

@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staffid")
    private Integer staffid;

    @Column(name = "fname")
    private String fname;

    @Column(name = "lname")
    private String lname;

    @Column(name = "salary")
    private Integer salary;

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

    @Override
    public String toString() {
        return "Staff [staffid=" + staffid + ", fname=" + fname + ", lname=" + lname + ", salary=" + salary + "]";
    }

}
