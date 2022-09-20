package hello.proxy.jdkdynamic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ReflectionTest {

  @Test
  void reflection0() {
    Hello target = new Hello();

    //공통 로직1 시작
    log.info("start");
    String result1 = target.callA();
    log.info("result={}", result1);

    //공통 로직2 시작
    log.info("start");
    String result2 = target.callB();
    log.info("result={}", result2);
  }

  @Slf4j
  static class Hello {

    public String callA() {
      log.info("callA");
      return "A";
    }

    public String callB() {
      log.info("callB");
      return "B";
    }
  }

  @Test
  void reflection1()
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    // 클래스 정보
    Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

    Hello target = new Hello();

    Method methodCallA = classHello.getMethod("callA");
//    Object result1 = methodCallA.invoke(target);
//    log.info("result1={}", result1);
    dynamicCall(methodCallA, target);

    Method methodCallB = classHello.getMethod("callB");
//    Object result2 = methodCallB.invoke(target);
//    log.info("result2={}", result2);
    dynamicCall(methodCallB, target);
  }

  private void dynamicCall(Method method, Object target)
      throws InvocationTargetException, IllegalAccessException {
    log.info("start");
    Object result = method.invoke(target);
    log.info("result={}", result);
  }
}
