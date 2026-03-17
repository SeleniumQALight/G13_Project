package org.api;

public interface EndPoints {
    String POSTS_BY_USER="postsByAuthor/{0}";

    String BASE_URL_DEMOQA = "https://demoqa.com";

    String LOGIN = "/Account/v1/Login";

    String GET_ALL_BOOKS = "/BookStore/v1/Books";

    String ADD_BOOK = "/BookStore/v1/Books";

    String DELETE_BOOKS = "/BookStore/v1/Books";

    String GET_USER = "/Account/v1/User/{userId}";
}
