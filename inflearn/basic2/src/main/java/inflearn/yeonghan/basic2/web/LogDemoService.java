package inflearn.yeonghan.basic2.web;

import inflearn.yeonghan.basic2.common.MyLogger;
import javax.inject.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

  private final Provider<MyLogger> myLogger;

  public void logic(String id) {
    myLogger.get().log("service id  " + id);
  }
}
