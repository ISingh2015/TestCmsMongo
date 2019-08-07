package com.cms;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GlobalTestCmsMongo {
    static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    public static void main(String... args) {
        String pwd = "testPassword2019";
        String newPwd = bCryptPasswordEncoder.encode(pwd);
        // $2a$10$RJJ6Zm61hWtjpIfrYNtHEe3A4UWqWmZ3on26OIeFSxZRkOyURIxhu
        System.out.println("Encode done !!! new Password "+ newPwd);
        System.out.println("Decode Match done !!! match's "+ bCryptPasswordEncoder.matches(pwd, newPwd));
    }
}
