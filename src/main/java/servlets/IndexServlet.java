package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import models.Products;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        log.info("in indexServlet");
        List<Products> products = new ArrayList<>();
        try (ResultSet rs = getConnection().createStatement().
                executeQuery("select id, name, desc from products")) {
            products = convertRSToProducts(rs);
        } catch (SQLException | NamingException e) {
            log.error("Ошибка получения данных: {}", e.getMessage());
        }
        request.setAttribute("products", products);
        try {
            setCountProducts(request, products);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("main.jsp").forward(request, resp);
    }

    private Connection getConnection() throws NamingException, SQLException {
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        DataSource dataSource = (DataSource) envContext.lookup("jdbc/TestDB");
        return dataSource.getConnection();
    }

    private List<Products> convertRSToProducts(ResultSet rs) throws SQLException {
        List<Products> products = new ArrayList<>();
        while (rs.next()) {
            String id = rs.getString("ID");
            String name = rs.getString("NAME");
            String desc = rs.getString("DESC");
            products.add(new Products(id, name, desc));
        }
        return products;
    }

    private void setCountProducts(HttpServletRequest request, List<Products> products) throws SQLException {
        HttpSession session = request.getSession();
        String productName = request.getParameter("product") == null ? "notValue" : request.getParameter("product");
        String sessionProductName = "cartproducts" + productName;
        Object valueSession = session.getAttribute(sessionProductName);
        int count = valueSession == null ? 0 : (Integer) valueSession;
        log.info("productName = {}, count = {}", productName, count);
        if ("notValue".equals(productName)){
            session.setAttribute(sessionProductName,count + 1);
        }

    }
}
