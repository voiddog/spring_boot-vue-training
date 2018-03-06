package org.voiddog.spring.test.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TeacherRepository extends JpaRepository<TeacherDAO, Long>, JpaSpecificationExecutor<TeacherDAO>{
}
