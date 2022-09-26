package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static servlets.Links.INDEXJSP;
import static servlets.Links.USERNAME;

@WebServlet("/auth")
@Slf4j
public class AuthenticationServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("In servlet auth");
        String login = request.getParameter("Login");
        if (verificationLoginAndPassword(login, request.getParameter("Password"))) {
            HttpSession session = request.getSession();
            Object userNameSession = session.getAttribute(USERNAME.getName());
            session.setAttribute(USERNAME.getName(), userNameSession == null ? login : userNameSession);
            session.setAttribute("authStatus", 1);
            log.info("session username = {}", userNameSession);
            request.getRequestDispatcher(INDEXJSP.getName()).forward(request, response);
        } else {
            response.addCookie(new Cookie("code", "400"));
            response.sendError(400);
        }
    }

    private Boolean verificationLoginAndPassword(String login, String password) {
        return !login.isBlank() && !password.isBlank();
    }
}
