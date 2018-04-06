package org.voiddog.spring.test.xls;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface XlsRepository extends JpaRepository<XlsFileDAO, Long>, JpaSpecificationExecutor<XlsFileDAO>{
}
