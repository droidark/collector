package xyz.krakenkat.collector.constant;

public class Constants {
    public static final String USERNAME_BLANK = "username shouldn't be blank";
    public static final String USERNAME_REGEX = "^(?=.{4,20}$)(?![_.0-9])(?!.*[_.]{2})[a-z0-9._]+(?<![_.])$";
    public static final String USERNAME_EXCEPTION_MESSAGE = "The username must have at least 4 characters in length, must be in lowercase and it can include underscores";
    public static final String USERNAME_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The given username already exists";
    public static final String EMAIL_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The given email already exists";
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    public static final String PASSWORD_EXCEPTION_MESSAGE = "Password must have at least 8 characters in length, include at least one number and one special character";
    public static final String PUBLISHER_KEY_NOT_FOUND_EXCEPTION_MESSAGE = "Publisher key must be either present or be valid";
    public static final String TITLE_KEY_NOT_FOUND_EXCEPTION_MESSAGE = "Title key must be either present or be valid";
    public static final String ISSUE_KEY_NOT_FOUND_EXCEPTION_MESSAGE = "Issue key must be either present or be valid";
    public static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "The username %s doesn't exist";
    public static final String DEFAULT_AVATAR = "default-avatar.png";
    public static final String DEFAULT_COVER = "default-cover.png";
    public static final String DEFAULT_ABOUT_YOU = "Nothing to say yet";
    public static final String DEFAULT_STATUS = "PENDING";
    public static final String DEFAULT_PROFILE = "USER";
    public static final String PAGE_DESCRIPTION = "Zero based page index (0... N)";
    public static final String SIZE_DESCRIPTION = "Number of records per page";
    public static final String SORT_DESCRIPTION = "Sorting criteria in the format: property(,asc|desc). Default sort " +
            "order is ascending. Multiple sort criteria are supported.";
    public static final String VARIANT_DESCRIPTION = "Filter to get all issues, regular issues or variant issues";

    private Constants() {}
}
