package yeonghan.mvc_servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class MvcServletApplication {

  public static void main(String[] args) {
    SpringApplication.run(MvcServletApplication.class, args);
  }

}
