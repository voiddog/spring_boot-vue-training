package org.voiddog.spring.test.xls;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class XlsFilterHelper {

    public static Specification<XlsFileDAO> filterByMd5(String md5){
        return (root, query, cb) -> {
            if (StringUtils.isEmpty(md5)){
                return null;
            }
            return cb.equal(root.get(XlsFileDAO_.fileMd5), md5);
        };
    }
}
