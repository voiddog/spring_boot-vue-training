package org.voiddog.spring.test.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@MappedSuperclass
public class BaseDAO {

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    @PrePersist
    protected void onCreated(){
        createTime = updateTime = new Date();
    }

    @PreUpdate
    protected void onUpdated(){
        updateTime = new Date();
    }
}
