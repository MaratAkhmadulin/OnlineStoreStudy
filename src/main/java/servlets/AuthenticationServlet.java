package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

@WebServlet("/auth")
@Slf4j
public class AuthenticationServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("In servlet auth");
        if (verificationLoginAndPassword(request.getParameter("Login"), request.getParameter("Password"))) {
            HttpSession session = request.getSession();
            session.setAttribute("username", session.getAttribute("username") == null ? request.getParameter("Login") : session.getAttribute("username"));
            session.setAttribute("authStatus", 1);
            log.info("session username = {}", session.getAttribute("username"));
            request.getRequestDispatcher("index.jsp").forward(request, response);
            log.info("end auth");
        } else {
            response.addCookie(new Cookie("code", "400"));
            response.sendError(400);
        }
    }

    private Boolean verificationLoginAndPassword(String login, String password) {
        return !login.isBlank() && !password.isBlank();
    }
}
