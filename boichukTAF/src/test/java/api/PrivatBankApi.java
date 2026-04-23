package api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import java.util.List;
import java.util.Map;

public class PrivatBankApi {

    private static final String URL =
            "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";

    public static double getRate(String currency, String type) {
        List<Map<String, Object>> response = RestAssured.get(URL)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("");

        for (Map<String, Object> item : response) {
            if (item.get("ccy").equals(currency)) {

                if (type.equals("buy")) {
                    return Double.parseDouble(item.get("buy").toString());
                } else {
                    return Double.parseDouble(item.get("sale").toString());
                }
            }
        }

        throw new RuntimeException("Currency not found: " + currency);
    }
}
