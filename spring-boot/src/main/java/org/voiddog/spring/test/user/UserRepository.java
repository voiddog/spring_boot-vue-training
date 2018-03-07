package org.voiddog.spring.test.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<UserDAO, Long>, JpaSpecificationExecutor<UserDAO>{
}
