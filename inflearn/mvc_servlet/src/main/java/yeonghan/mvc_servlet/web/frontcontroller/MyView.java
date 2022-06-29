package yeonghan.mvc_servlet.web.frontcontroller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyView {

  private String viewPath;

  public MyView(String viewPath) {
    this.viewPath = viewPath;
  }

  public void render(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
    dispatcher.forward(request, response);
  }

  public void render(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {
    modelToRequestAttribute(model, req);
    RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
    dispatcher.forward(req, res);
  }

  private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest req) {
    model.forEach(req::setAttribute);
  }
}
