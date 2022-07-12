package yeonghan.spring_mvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {

  private final Logger log = LoggerFactory.getLogger(LogTestController.class);

  @RequestMapping("/log-test")
  public String logTest() {
    String name = "Spring";
    System.out.println("name = " + name);

    log.trace("trace log = " + name); // 이건 안됨! CPU, 메모리 낭비!

    log.trace("trace log = {}", name);
    log.debug("debug log = {}", name);
    log.warn("warn log = {}", name);
    log.info(" info log = {}", name);
    log.error("error log = {}", name);

    return "ok";
  }

}
