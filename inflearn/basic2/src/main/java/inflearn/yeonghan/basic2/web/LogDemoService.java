package inflearn.yeonghan.basic2.web;

import inflearn.yeonghan.basic2.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

  private final MyLogger myLogger;

  public void logic(String id) {
    myLogger.log("service id  " + id);
  }
}
