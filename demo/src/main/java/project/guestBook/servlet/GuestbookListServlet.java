package project.guestBook.servlet;

import project.guestBook.dao.GuestbookDao;
import project.guestBook.dto.Guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/guestbooks")
public class GuestbookListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 코드를 작성하세요.
        String current_url = request.getRequestURI();
        String submit_url = current_url + "/write";

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        System.out.println(request.getRequestURI());

        PrintWriter out = response.getWriter();
        GuestbookDao dao = new GuestbookDao();
        List<Guestbook> guestbooks = dao.getGuestbooks();

        out.println("<html>");
        out.println("<head><title>form</title></head>");
        out.println("<body>");
        for (Guestbook guestbook: guestbooks) {
            out.println(guestbook);
            out.println("<br>");
        }
        out.println("<form method='post' action=" + submit_url + ">");
        out.println("이름 : <input type='text' name='name'><br>");
        out.println("내용 : <input type='textarea' name='content'><br>");
        out.println("<input type='submit' value='ok'><br>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

}
