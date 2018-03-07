package org.voiddog.spring.test.user;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class UserFilterHelper {

    public static Specification<UserDAO> filterByName(String name){
        return (root, query, cb) -> {
            if (StringUtils.isEmpty(name)){
                return null;
            }
            return cb.like(root.get(UserDAO_.name), "%"+name+"%");
        };
    }

    public static Specification<UserDAO> filterByAccount(String account){
        return (root, query, cb) -> {
            if (StringUtils.isEmpty(account)){
                return null;
            }
            return cb.equal(root.get(UserDAO_.account), account);
        };
    }
}
