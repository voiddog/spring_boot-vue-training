package org.voiddog.spring.test.teacher;

import org.voiddog.spring.test.model.Constants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddTeacherRequest {

    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    private Integer age;

    @NotNull
    private Integer gender = Constants.MALE;

    private String organization;

    private String job;

    private String phone;

    private String record;

    private String appraise;

    private float cost;

    @NotNull
    private Integer levelType = TeacherDAO.LEVEL_TYPE_LECTURER;

    @NotNull
    private Integer type = TeacherDAO.TYPE_INNER_PROVINCE;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getAppraise() {
        return appraise;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Integer getLevelType() {
        return levelType;
    }

    public void setLevelType(Integer levelType) {
        this.levelType = levelType;
    }
}
