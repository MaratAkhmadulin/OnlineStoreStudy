package servlets;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Links {
    STAGE ("stage"),
    MAIN ("main"),
    MAINPATH ("/index"),
    AUTH ("authentication"),
    AUTHJSP ("auth.jsp"),
    CART("cart"),
    CARTPATH("/cart"),
    USERNAME("username"),
    PRODUCT("product");

    private String name;
}
