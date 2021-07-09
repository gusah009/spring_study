package servletTest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.dao.RoleDao;
import jdbc.dto.Role;

@WebServlet("/jdbcTest")
public class jdbcExam1 extends HttpServlet {
    /**
     * @see HttpServlet#HttpServlet()
     */
    public jdbcExam1() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		// RoleDao dao = new RoleDao();
		// Role role = dao.getRole(100);
		// out.println(role);
		out.println("ㅋㅋ");
	}
}