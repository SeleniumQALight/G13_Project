package org.data;

import com.github.javafaker.Faker;
import org.pages.utils.Utils_Custom;

public class User {
    private String userName;
    private String email;
    private String password;
    private Faker faker = new Faker();

    public final static String SHORT_PASSWORD_NOT_VALID = "tr";
    public final static String SHORT_EMAIL_NOT_VALID = "tr";
    public final static String SHORT_USER_NAME_NOT_VALID = "tr";

    public final static String USER_NAME_MIN_LENGTH = "t".repeat(3);
    public final static String USER_NAME_MAX_LENGTH = "t".repeat(30);

    public final static String PASSWORD_MIN_LENGTH = "t".repeat(6) + "1".repeat(6);
    public final static String PASSWORD_MAX_LENGTH = "t".repeat(25) + "1".repeat(25);

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.email = this.userName + "@gmail.rr";
        this.password = password;
    }

    public User(String tcNumber) {
        this.userName = tcNumber + "ra" + faker.name().lastName()
                + Utils_Custom.getDateAndTimeFormattedOnlyNumbers();
        this.email = this.userName + "@gmail.rr";
        this.password = TestData.VALID_PASSWORD;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User updateUserName(String newUserName) {
        this.userName = newUserName;
        return this;
    }

    public User updateEmail(String newEmail) {
        this.email = newEmail;
        return this;
    }

    public User updatePassword(String newPassword) {
        this.password = newPassword;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
