package org.api.dto.requestDto;

import java.util.List;

public class AddBookRequest {
    public String userId;
    public List<Isbn> collectionOfIsbns;

    public AddBookRequest(String userId, List<Isbn> collectionOfIsbns) {
        this.userId = userId;
        this.collectionOfIsbns = collectionOfIsbns;
    }

    public static class Isbn {
        public String isbn;
        public Isbn(String isbn) { this.isbn = isbn; }
    }
}
