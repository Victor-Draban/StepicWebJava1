package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by морбид on 13.10.2016.
 */
public class Mirror extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String[]> temp = req.getParameterMap();
        Set<String> keySet = temp.keySet();
        StringBuilder sb = new StringBuilder();
        for (String s : keySet) {
            String[] t = temp.get(s);
            for (int i = 0; i < t.length; i++) {
                sb.append(t[i]);
            }
        }

        resp.getWriter().println(sb.toString());

        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

}
