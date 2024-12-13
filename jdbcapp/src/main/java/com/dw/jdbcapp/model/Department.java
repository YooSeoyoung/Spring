package com.dw.jdbcapp.model;

public class Department { //부서
    private String departmentId; //부서번호
    private String departmentName; // 부서명

    public Department() {
    }

    public Department(String departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{"+
               "DepartmentId=" + departmentId +
               ", DepartmentName=" + departmentName +'}';
    }
}
