package org.data;

import com.github.javafaker.Faker;
import org.utils.Utils_Custom;

public class User {
    private String username;
    private String email;
    private String password;
    private Faker faker = new Faker();

    public final static String SHORT_PASSWORD_NOT_VALID = "tr";
    public final static String SHORT_EMAIL_NOT_VALID = "tr";
    public final static String SHORT_USERNAME_NOT_VALID = "tr";

    public final static String USER_NAME_MIN_LENGTH = "t".repeat(3);
    public final static String USER_NAME_MAX_LENGTH = "t".repeat(30);

    public final static String PASSWORD_MIN_LENGTH = "t".repeat(6) + "1".repeat(6);
    public final static String PASSWORD_MAX_LENGTH = "t".repeat(25) + "1".repeat(25);

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public User(String username, String password){
        this.username = username;
        this.email = this.username + "@qmai.rr";
        this.password = password;
    }

    public User(String tcNumber){
        this.username = tcNumber + "ra" + faker.name().lastName()
                + Utils_Custom.getDateAndTimeFormattedOnlyNumbers();
        this.email = this.username + "@qmai.rr";
        this.password = TestData.VALID_PASSWORD_UI;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User updateUsername(String newUsername) {
        this.username = newUsername;
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
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
