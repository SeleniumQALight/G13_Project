package org.api;

import java.net.URI;

public interface EndPoints {
    String POSTS_BY_USER = "postsByAuthor/{0}";
    String LOGIN = "login";
    String CREATE_POST = "create-post" ;

    String CREATE_POST_BANK = "/api/post"; // HW-1
    String PRIVAT_EXCHANGE_RATES = "https://api.privatbank.ua/p24api/exchange_rates";
}
