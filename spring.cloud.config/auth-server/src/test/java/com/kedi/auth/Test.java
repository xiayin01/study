package com.kedi.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 测试
 *
 * @author xy
 */
public class Test {

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode("{bcrypt}android");
        System.out.printf("---:{bcrypt}" + encodePassword);
    }
}
