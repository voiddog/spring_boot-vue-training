package org.voiddog.spring.test.student;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(StudentDAO.class)
public class StudentDAO_ {
    public static volatile SingularAttribute<StudentDAO,  Long> id;

    public static volatile SingularAttribute<StudentDAO,  String> name;

    public static volatile SingularAttribute<StudentDAO,  Integer> age;

    public static volatile SingularAttribute<StudentDAO,  Integer> gender;

    public static volatile SingularAttribute<StudentDAO,  String> organization;

    public static volatile SingularAttribute<StudentDAO,  String> job;

    public static volatile SingularAttribute<StudentDAO,  String> phone;
}
