package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;

import static servlets.Links.*;

@WebServlet("/main")
@Slf4j
public class FrontControllerServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        log.info("in mainServlet");
        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(60 * 1);
        String stage = req.getParameter(STAGE.getName());
        if (existAuthSession(session.getAttribute(USERNAME.getName()), stage, req.getParameter(PRODUCT.getName()))){
            String path;
            switch (stage) {
                case ("authentication"):
                    path = AUTHJSP.getName();
                    break;
                case ("cart"):
                    path = CARTPATH.getName();
                    break;
                default:
                    path = MAINPATH.getName();
                    break;
            }
            forwardReq(req, resp, path);
        }
        else{
            forwardReq(req, resp, AUTHJSP.getName());

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

    private Boolean existAuthSession(Object userNameSession, String reqParamStage, String reqParamProduct) {

        String username = userNameSession == null ? "Not value" : userNameSession.toString();
        return !((reqParamProduct != null ||
                ("cart".equals(reqParamStage))) && "Not value".equals(username)) ;
    }
}
