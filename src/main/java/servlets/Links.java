package servlets;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Links {
    STAGE ("stage"),
    MAIN ("main"),
    MAINPATH ("/index"),
    INDEXJSP ("index.jsp"),
    AUTH ("authentication"),
    AUTHPATH ("/auth"),
    AUTHJSP ("auth.jsp"),
    CART("cart"),
    CARTJSP("cart.jsp"),
    CARTPATH("/cart"),
    USERNAME("username"),
    NOTVALUE("Not value"),
    PRODUCT("product"),
    CARTPRODUCTS("cartproducts");

    private String name;
}
