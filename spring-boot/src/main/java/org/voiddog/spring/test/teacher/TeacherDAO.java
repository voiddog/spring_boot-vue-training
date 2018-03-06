package org.voiddog.spring.test.teacher;

import org.voiddog.spring.test.model.BaseDAO;

import javax.persistence.*;

@Entity
@Table(name = "teacher")
public class TeacherDAO extends BaseDAO {

    // 省内
    public static final int TYPE_INNER_PROVINCE = 0;
    // 外省
    public static final int TYPE_OUT_PROVINCE = 1;
    // 全国
    public static final int TYPE_COUNTRY = 2;

    // 教授类型
    public static final int LEVEL_TYPE_PROFESSOR = 1;
    // 副教授
    public static final int LEVEL_TYPE_ASSOCIATE_PROFESSOR = 2;
    // 讲师
    public static final int LEVEL_TYPE_LECTURER = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int age;

    /**
     * {@link org.voiddog.spring.test.model.Constants#MALE}
     */
    private int gender;

    // 单位
    private String organization;

    private String job;

    private String phone;

    /**
     * {@link #TYPE_INNER_PROVINCE}
     */
    private int type;

    private String record;

    private String appraise;

    private float cost;

    private int levelType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
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

    public int getLevelType() {
        return levelType;
    }

    public void setLevelType(int levelType) {
        this.levelType = levelType;
    }
}
