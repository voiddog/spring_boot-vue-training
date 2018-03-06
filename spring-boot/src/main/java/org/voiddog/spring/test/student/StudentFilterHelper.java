package org.voiddog.spring.test.student;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class StudentFilterHelper {
    public static Specification<StudentDAO> filterByName(String name){
        return (root, query, cb) -> {
            if (StringUtils.isEmpty(name)){
                return null;
            }
            return cb.like(root.get(StudentDAO_.name), "%"+name+"%");
        };
    }

    public static Specification<StudentDAO> filterByAge(int minAge, int maxAge){
        return (root, query, cb) -> cb.between(root.get(StudentDAO_.age), minAge, maxAge);
    }

    public static Specification<StudentDAO> filterByPhone(String phone){
        return (root, query, cb) -> {
            if (StringUtils.isEmpty(phone)){
                return null;
            }
            return cb.equal(root.get(StudentDAO_.phone), phone);
        };
    }

    public static Specification<StudentDAO> filterByJob(String job){
        return (root, query, cb) -> {
            if (StringUtils.isEmpty(job)){
                return null;
            }
            return cb.like(root.get(StudentDAO_.job), "%" + job + "%");
        };
    }

    public static Specification<StudentDAO> filterByOrganization(String organization){
        return (root, query, cb) -> {
            if (StringUtils.isEmpty(organization)){
                return null;
            }
            return cb.like(root.get(StudentDAO_.organization), "%" + organization + "%");
        };
    }

    public static Specification<StudentDAO> filterByGender(Integer gender){
        return (root, query, cb) -> {
            if (gender == null){
                return null;
            }
            return cb.equal(root.get(StudentDAO_.gender), gender);
        };
    }
}
