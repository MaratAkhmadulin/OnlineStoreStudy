package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@WebServlet("/cart")
@Slf4j
public class CartServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, Integer> productsMap = new HashMap<>();
        Enumeration attrs =  request.getSession().getAttributeNames();
        while(attrs.hasMoreElements()) {
            String productName = attrs.nextElement().toString();
            if (productName.contains("cartproducts")){
                productsMap.put(productName.substring(12), (int)request.getSession().getAttribute(productName));
            }
        }

        request.setAttribute("cartProducts", productsMap);
        log.info("cartProducts = {}", productsMap);
        request.getRequestDispatcher("cart.jsp").forward(request, response);



    }
}
