package inflearn.yeonghan.basic2.web;

import inflearn.yeonghan.basic2.common.MyLogger;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

  private final LogDemoService logDemoService;
  private final MyLogger myLogger;

  @RequestMapping("log-demo")
  @ResponseBody
  public String logDemo(HttpServletRequest request) {
    String requestURL = request.getRequestURL().toString();

    System.out.println("myLogger = " + myLogger.getClass());
    myLogger.setRequestURL(requestURL);

    myLogger.log("Controller Test");
    logDemoService.logic("test id");
    return "OK!";
  }

}
