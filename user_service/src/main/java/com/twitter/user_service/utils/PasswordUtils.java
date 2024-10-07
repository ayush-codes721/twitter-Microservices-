package com.twitter.user_service.utils;


import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hashedP) {

        return BCrypt.checkpw(password, hashedP);
    }
}
