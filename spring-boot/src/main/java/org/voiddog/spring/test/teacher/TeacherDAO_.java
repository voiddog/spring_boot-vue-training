package org.voiddog.spring.test.teacher;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TeacherDAO.class)
public class TeacherDAO_ {

    public static volatile SingularAttribute<TeacherDAO,  Long> id;

    public static volatile SingularAttribute<TeacherDAO,  String> name;

    public static volatile SingularAttribute<TeacherDAO,  Integer> age;

    public static volatile SingularAttribute<TeacherDAO,  Integer> gender;

    public static volatile SingularAttribute<TeacherDAO,  String> organization;

    public static volatile SingularAttribute<TeacherDAO,  String> job;

    public static volatile SingularAttribute<TeacherDAO,  String> phone;

    public static volatile SingularAttribute<TeacherDAO,  Integer> type;

    public static volatile SingularAttribute<TeacherDAO, Integer> levelType;
}
