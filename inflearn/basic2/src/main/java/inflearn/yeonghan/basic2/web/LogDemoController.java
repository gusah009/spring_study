package inflearn.yeonghan.basic2.web;

import inflearn.yeonghan.basic2.common.MyLogger;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

  private final LogDemoService logDemoService;
  private final Provider<MyLogger> myLogger;

  @RequestMapping("log-demo")
  @ResponseBody
  public String logDemo(HttpServletRequest request) {
    String requestURL = request.getRequestURL().toString();
    myLogger.get().setRequestURL(requestURL);

    myLogger.get().log("Controller Test");
    logDemoService.logic("test id");
    return "OK!";
  }

}
