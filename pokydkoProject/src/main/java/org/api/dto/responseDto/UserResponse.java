package org.api.dto.responseDto;

import java.util.List;

public class UserResponse {
    public String userId;
    public String isbn;
    public String username;  // додати
    public List<BooksResponse.Book> books;
}