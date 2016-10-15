package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by морбид on 15.10.2016.
 */
public class SignInServlet extends HttpServlet {
    private final AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        UserProfile userByLogin = accountService.getUserByLogin(login);
        Gson gson = new Gson();
        String jsonFail = gson.toJson("Unauthorized");
        String jsonOK = gson.toJson("Authorized: " + login);

        if (userByLogin != null && userByLogin.getLogin().equals(login) && userByLogin.getPass().equals(password)) {
            System.out.println("success");
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println(jsonOK);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            System.out.println("fail");
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println(jsonFail);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
