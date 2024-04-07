package com.codeElevate.blogServer.service;

import com.codeElevate.blogServer.controller.UserData;

public class LoginVerify {
    public static boolean verify(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }


}

