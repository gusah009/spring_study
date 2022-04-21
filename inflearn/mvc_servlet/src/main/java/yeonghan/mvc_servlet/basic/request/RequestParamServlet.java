package yeonghan.mvc_servlet.basic.request;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 1. 파라미터 전송 기능 http://localhost:8080/requeset-param?username=hello&age=20
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getParameterNames().asIterator().forEachRemaining((paramName -> System.out.println(
        "paramName = " + req.getParameter(paramName))));
    System.out.println("[전체 파라미터 조회] - end");
    System.out.println();

    System.out.println("[단일 파라미터 조회]");
    String username = req.getParameter("username");
    System.out.println("request.getParameter(username) = " + username);
    String age = req.getParameter("age");
    System.out.println("request.getParameter(age) = " + age);
    System.out.println();

    System.out.println("[이름이 같은 복수 파라미터 조회]");
    System.out.println("request.getParameterValues(username)");
    String[] usernames = req.getParameterValues("username");
    for (String name : usernames) {
      System.out.println("username=" + name);
    }

    resp.getWriter().write("ok");

  }
}
