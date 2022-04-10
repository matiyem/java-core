package com.example.nestedHashMaps;

/*
    Create by Atiye Mousavi 
    Date: 4/9/2022
    Time: 12:36 PM
**/
public class Employee {
    private Integer employeeId;
    private Address address;
    private String employeeName;

    public Employee() {
    }

    public Employee(Integer employeeId, Address address, String employeeName) {
        this.employeeId = employeeId;
        this.address = address;
        this.employeeName = employeeName;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
