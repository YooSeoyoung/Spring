package com.dw.jdbcapp.model;

import java.time.LocalDate;

public class Employee { //사원
    private String employeeId; //사원번호
    private String employeeKoreanName; //이름
    private String employeeEnglishName; //영문이름
    private String position; //직위
    private String gender; //성별
    private LocalDate birthday;//생일
    private LocalDate dateOfEmployment; // 입사일
    private String address; //주소
    private String city;//도시
    private String area;//지역
    private String homePhone;//집전화
    private String supervisorId; //상사번호
    private String departmentId; //부서번호

    public Employee() {
    }

    public Employee(String employeeId, String employeeKoreanName, String employeeEnglishName, String position, String gender, LocalDate birthday, LocalDate dateOfEmployment, String address, String city, String area, String homePhone, String supervisorId, String departmentId) {
        this.employeeId = employeeId;
        this.employeeKoreanName = employeeKoreanName;
        this.employeeEnglishName = employeeEnglishName;
        this.position = position;
        this.gender = gender;
        this.birthday = birthday;
        this.dateOfEmployment = dateOfEmployment;
        this.address = address;
        this.city = city;
        this.area = area;
        this.homePhone = homePhone;
        this.supervisorId = supervisorId;
        this.departmentId = departmentId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeKoreanName() {
        return employeeKoreanName;
    }

    public void setEmployeeKoreanName(String employeeKoreanName) {
        this.employeeKoreanName = employeeKoreanName;
    }

    public String getEmployeeEnglishName() {
        return employeeEnglishName;
    }

    public void setEmployeeEnglishName(String employeeEnglishName) {
        this.employeeEnglishName = employeeEnglishName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDate dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                "employeeKoreanName=" + employeeKoreanName +
                "employeeEnglishName=" + employeeEnglishName +
                "position=" + position+
                "gender=" + gender+
                "birthday="+birthday+
                "dateOfEmployment="+dateOfEmployment+
                "address="+ address+
                "city=" + city+
                "area=" + area+
                "homePhone="+ homePhone+
                "supervisorId="+ supervisorId +
                "departmentId="+ departmentId + '}';
    }
}
