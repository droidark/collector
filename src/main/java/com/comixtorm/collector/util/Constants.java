package com.comixtorm.collector.util;

public class Constants {
    public static final String USERNAME_REGEX = "^(?=.{4,20}$)(?![_.0-9])(?!.*[_.]{2})[a-z0-9._]+(?<![_.])$";
    public static final String USERNAME_EXCEPTION_MESSAGE = "The username must have at least 4 characters in length, must be in lowercase and it can include underscores";
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    public static final String PASSWORD_EXCEPTION_MESSAGE = "Password must have at least 8 characters in length, include at least one number and one special character";
    public static final String PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE = "Publisher Key must be either present or be valid";
    public static final String TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE = "Title Key must be either present or be valid";
    public static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "The username %s doesn't exist";

}
