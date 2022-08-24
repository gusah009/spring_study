package hello.login.web.session;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class SessionManagerTest {

  SessionManager sessionManager = new SessionManager();

  @Test
  void sessionTest() {

    MockHttpServletResponse response = new MockHttpServletResponse();
    Member member = new Member();
    sessionManager.createSession(member, response);

    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setCookies(response.getCookies());

    // 세션 조회
    Object result = sessionManager.getSession(request);
    Assertions.assertThat(result).isEqualTo(member);

    sessionManager.expire(request);
    Object expireResult = sessionManager.getSession(request);
    Assertions.assertThat(expireResult).isNull();
  }
}