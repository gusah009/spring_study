package yeonghan.mvc_servlet.web.servletmvc;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import yeonghan.mvc_servlet.domain.member.Member;
import yeonghan.mvc_servlet.domain.member.MemberRepository;

@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {

  private MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String username = req.getParameter("username");
    int age = Integer.parseInt(req.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);

    // Model에 데이터를 보관한다.
    req.setAttribute("member", member);

    String viewPath = "/WEB-INF/views/save-result.jsp";
    RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
    dispatcher.forward(req, resp);
  }
}
