package yeonghan.mvc_servlet.web.springmvc.v3;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import yeonghan.mvc_servlet.domain.member.Member;
import yeonghan.mvc_servlet.domain.member.MemberRepository;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

  private MemberRepository memberRepository = MemberRepository.getInstance();

  //  @RequestMapping(value = "/new-form", method = GET)
  @GetMapping(value = "/new-form")
  public String newForm() {
    return "new-form";
  }

  //  @RequestMapping(value = "/save", method = POST)
  @PostMapping(value = "/save")
  public String save(
      @RequestParam("username") String username,
      @RequestParam("age") int age,
      Model model) {

    Member member = new Member(username, age);
    memberRepository.save(member);

    model.addAttribute("member", member);
    return "save-result";
  }

  //  @RequestMapping(value = "", method = GET)
  @GetMapping(value = "")
  public String members(Model model) {

    List<Member> members = memberRepository.findAll();

    model.addAttribute("members", members);

    return "members";
  }
}
