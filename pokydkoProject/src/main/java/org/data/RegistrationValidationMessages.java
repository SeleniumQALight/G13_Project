package org.data;

public class RegistrationValidationMessages {
    public final static String ERROR_EMAIL_ALREADY_EXISTS = "This email is already being used.";
    public final static String ERROR_USERNAME_ALREADY_EXISTS = "This username is already taken.";

    public final static String ERROR_USERNAME = "Username must be at least 3 characters.";
    public final static String ERROR_EMAIL = "You must provide a valid email address.";
    public final static String ERROR_PASSWORD = "Password must be at least 12 characters.";

    public final static String ERROR_LONG_PASSWORD = "Password cannot exceed 50 characters.";
    public final static String ERROR_LONG_USER = "Username cannot exceed 30 characters.";

    public final static String SEMICOLON = ";";
}
