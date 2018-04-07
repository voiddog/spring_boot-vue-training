package org.voiddog.spring.test.model;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class Constants {
    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_SYS_ERROR = 100;
    public static final int STATUS_MSG_ERROR = 101;

    // 性别
    public static final int MALE = 1;
    public static final int FEMALE = 0;

    public static int convertGenderString(String gender) {
        switch (gender) {
            case "男":
                return MALE;
            case "女":
                return FEMALE;
        }
        return MALE;
    }

    public static String getClassRootPath() {
        //获取跟目录
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(path == null || !path.exists()){
            path = new File("");
        }
        return path.getAbsolutePath();
    }

    public static String getDownloadPath() {
        File path = new File(getClassRootPath(), "download");
        return path.getAbsolutePath();
    }
}
