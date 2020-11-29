import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter extends HttpFilter {
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("test");
        System.out.println(req.getSession().getAttribute("token") != null);
        System.out.println(req.getSession().getAttribute("token"));
        if (req.getSession().getAttribute("token") != null) {
            if ((boolean) req.getSession().getAttribute("token")) {
                chain.doFilter(req, res);
            }
        } else {
            if ((req.getContextPath() + "/login.html").equals(req.getRequestURI()) || (req.getContextPath() + "/Auth").equals(req.getRequestURI())) {
                chain.doFilter(req, res);
            } else {
                res.sendRedirect("login.html");
            }
        }
    }
}