package yeonghan.mvc_servlet.web.frontcontroller.v2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import yeonghan.mvc_servlet.web.frontcontroller.MyView;
import yeonghan.mvc_servlet.web.frontcontroller.v2.ControllerV2;

public class MemberFormControllerV2 implements ControllerV2 {

  @Override
  public MyView process(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    return new MyView("/WEB-INF/views/new-form.jsp");
  }
}
