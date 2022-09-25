package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/main")
@Slf4j
public class FrontControllerServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        log.info("in mainServlet");
        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(60 * 1);
        String stage = req.getParameter("stage");
        String username = session.getAttribute("username") == null ? "Not value" : session.getAttribute("username").toString();
        if ((req.getParameter("product") != null && "Not value".equals(username)) ||
                ("cart".equals(req.getParameter("stage"))  && "Not value".equals(username))){
            forwardReq(req, resp, "auth.jsp");
        }
        else{
            String path;
            switch (req.getParameter("stage")) {
                case ("main"):
                    path = "/index";
                    break;
                case ("authentication"):
                    path = "auth.jsp";
                    break;
                case ("cart"):
                    path = "/cart";
                    break;
                default:
                    path = "/index";
                    break;
            }
            forwardReq(req, resp, path);
        }

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        log.info("in servlet main post");
        forwardReq(req, resp, "/auth");
    }

    private void forwardReq(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        if ("error".equals(path)) {
            out.println("select fo parameter \"stage\" value \"catalog\" or \"cart\"");
            log.info("select fo parameter \"stage\" value \"catalog\" or \"cart\"");
        } else {
            req.getRequestDispatcher(path).forward(req, resp);
        }
    }
}
