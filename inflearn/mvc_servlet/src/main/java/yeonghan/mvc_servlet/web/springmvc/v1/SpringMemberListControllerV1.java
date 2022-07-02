package yeonghan.mvc_servlet.web.springmvc.v1;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import yeonghan.mvc_servlet.domain.member.Member;
import yeonghan.mvc_servlet.domain.member.MemberRepository;
import yeonghan.mvc_servlet.web.frontcontroller.ModelView;

@Controller
public class SpringMemberListControllerV1 {

  private MemberRepository memberRepository = MemberRepository.getInstance();

  @RequestMapping("/springmvc/v1/members")
  public ModelAndView process() {

    List<Member> members = memberRepository.findAll();

    ModelAndView mv = new ModelAndView("members");
    mv.addObject("members", members);

    return mv;
  }
}
