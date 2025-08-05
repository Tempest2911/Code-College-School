

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String nguoiDung = (String) session.getAttribute("currentUser");

        if (nguoiDung == null || nguoiDung.isEmpty()) {
            nguoiDung = "Mr.Nobody";
        }

        req.setAttribute("nguoiDung", nguoiDung);
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}