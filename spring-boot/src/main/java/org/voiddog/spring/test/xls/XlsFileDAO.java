package org.voiddog.spring.test.xls;

import org.voiddog.spring.test.model.BaseDAO;

import javax.persistence.*;

@Entity
@Table(name = "xls", uniqueConstraints = @UniqueConstraint(columnNames = "FILEMD5"))
public class XlsFileDAO extends BaseDAO{

    @Id
    @GeneratedValue
    private Long id;

    private String fileMd5;

    private String filePath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
