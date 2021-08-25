package project.guestBook.servlet;

import project.guestBook.dao.GuestbookDao;
import project.guestBook.dto.Guestbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/guestbooks/write")
public class GuestbookWriteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 코드를 작성하세요.
        String name = request.getParameter("name");
        String content = request.getParameter("content");
        GuestbookDao dao = new GuestbookDao();
        Guestbook guestbook = new Guestbook(name, content);
        dao.addGuestbook(guestbook);

        String current_uri = request.getRequestURI();
        String redirect_url = current_uri.substring(0, current_uri.length() - 6);
        System.out.println(redirect_url);
        response.sendRedirect(redirect_url);
    }

}
