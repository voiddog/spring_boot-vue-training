package org.voiddog.spring.test;

import java.util.UUID;

public class TokenUtils {
    public static String createToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
