package yeonghan.mvc_servlet.web.frontcontroller.v4;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import yeonghan.mvc_servlet.web.frontcontroller.ModelView;
import yeonghan.mvc_servlet.web.frontcontroller.MyView;
import yeonghan.mvc_servlet.web.frontcontroller.v4.ControllerV4;
import yeonghan.mvc_servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import yeonghan.mvc_servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import yeonghan.mvc_servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

  private Map<String, ControllerV4> controllerMap = new HashMap<>();

  public FrontControllerServletV4() {
    controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
    controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
    controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
  }

  @Override
  public void service(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {
    System.out.println("FrontControllerServletV4.service");

    String requestURI = req.getRequestURI();

    ControllerV4 controller = controllerMap.get(requestURI);
    if (controller == null) {
      res.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    Map<String, String> paramMap = createParamMap(req);
    Map<String, Object> model = new HashMap<>();
    String viewName = controller.process(paramMap, model);

    MyView view = viewResolver(viewName);

    view.render(model, req, res);
  }

  private MyView viewResolver(String viewName) {
    return new MyView("/WEB-INF/views/" + viewName + ".jsp");
  }

  private Map<String, String> createParamMap(HttpServletRequest req) {
    Map<String, String> paramMap = new HashMap<>();
    req.getParameterNames().asIterator()
        .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
    return paramMap;
  }
}
