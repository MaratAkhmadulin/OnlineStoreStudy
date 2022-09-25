package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static servlets.Links.CARTJSP;
import static servlets.Links.CARTPRODUCTS;

@WebServlet("/cart")
@Slf4j
public class CartServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute(CARTPRODUCTS.getName(), getProductsMap(request.getSession()));
        request.getRequestDispatcher(CARTJSP.getName()).forward(request, response);
    }

    private Map<String, Integer> getProductsMap(HttpSession session) {
        Map<String, Integer> productsMap = new HashMap<>();
        Enumeration<String> attrs = session.getAttributeNames();
        while (attrs.hasMoreElements()) {
            String productName = attrs.nextElement();
            if (productName.contains(CARTPRODUCTS.getName())) {
                productsMap.put(productName.substring(12), (int) session.getAttribute(productName));
            }
        }
        log.info("cartProducts = {}", productsMap);
        return productsMap;
    }
}
