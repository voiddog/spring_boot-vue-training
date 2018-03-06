package org.voiddog.spring.test.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository extends JpaRepository<StudentDAO, Long>, JpaSpecificationExecutor<StudentDAO> {
}
