package org.voiddog.spring.test.teacher;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class TeacherFilterHelper {

    public static Specification<TeacherDAO> filterByName(String name){
        return (root, query, cb) -> {
            if (StringUtils.isEmpty(name)){
                return null;
            }
            return cb.like(root.get(TeacherDAO_.name), "%"+name+"%");
        };
    }

    public static Specification<TeacherDAO> filterByAge(int minAge, int maxAge){
        return (root, query, cb) -> cb.between(root.get(TeacherDAO_.age), minAge, maxAge);
    }

    public static Specification<TeacherDAO> filterByType(String type){
        return (root, query, cb) -> {
            if (StringUtils.isEmpty(type)){
                return null;
            }
            return cb.like(root.get(TeacherDAO_.type), "%"+type+"%");
        };
    }

    public static Specification<TeacherDAO> filterByPhone(String phone){
        return (root, query, cb) -> {
            if (StringUtils.isEmpty(phone)){
                return null;
            }
            return cb.equal(root.get(TeacherDAO_.phone), phone);
        };
    }

    public static Specification<TeacherDAO> filterByJob(String job){
        return (root, query, cb) -> {
            if (StringUtils.isEmpty(job)){
                return null;
            }
            return cb.like(root.get(TeacherDAO_.job), "%" + job + "%");
        };
    }

    public static Specification<TeacherDAO> filterByOrganization(String organization){
        return (root, query, cb) -> {
            if (StringUtils.isEmpty(organization)){
                return null;
            }
            return cb.like(root.get(TeacherDAO_.organization), "%" + organization + "%");
        };
    }

    public static Specification<TeacherDAO> filterByGender(Integer gender){
        return (root, query, cb) -> {
            if (gender == null){
                return null;
            }
            return cb.equal(root.get(TeacherDAO_.gender), gender);
        };
    }

    public static Specification<TeacherDAO> filterByLevelType(String levelType) {
        return (root, query, cb) -> {
            if (levelType == null) {
                return null;
            }
            return cb.like(root.get(TeacherDAO_.levelType), "%" + levelType + "%");
        };
    }
}
