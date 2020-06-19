package servlet;

import lombok.SneakyThrows;
import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/reestablish")
public class PasswordServlet extends HttpServlet {
    public static User currentUser;
    public static int code = 0;

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        UserManager userManager = new UserManager();
        User byEmail = userManager.getByEmail(email);
        if (byEmail != null) {
            currentUser = byEmail;
            int a = 0;
            int b = 10;
            int random = a + (int) (Math.random() * b);
            int random2 = a + (int) (Math.random() * b);
            int random3 = a + (int) (Math.random() * b);
            int random4 = a + (int) (Math.random() * b);
            code = Integer.parseInt(random + "" + random2 + "" + random3 + "" + random4);
            req.getSession().setAttribute("codemsg", code);
            resp.sendRedirect("/reestablish.jsp");
        } else {
            String str="Invalid email! Please try again";
            req.getSession().setAttribute("msg", str);
            resp.sendRedirect("/changepassword.jsp");
        }
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int code1 = Integer.parseInt(req.getParameter("code"));
        String pass1 = req.getParameter("pass1");
        String pass2 = req.getParameter("pass2");
        UserManager userManager = new UserManager();

        if (pass1.equals(pass2) && code == code1) {
            userManager.changePassword(pass1, currentUser.getId());
            resp.sendRedirect("/index.jsp");
        } else {
            String msg = "<span style=\"color:red\">Invalid value`</span>";
            req.getSession().setAttribute("message", msg);
            resp.sendRedirect("/reestablish.jsp");
        }
    }
}
