package jpashop.jpabook.api;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import jpashop.jpabook.domain.Member;
import jpashop.jpabook.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

  private final MemberService memberService;

  @PostMapping("/api/v1/members")
  public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
    Long id = memberService.join(member);
    return new CreateMemberResponse(id);
  }

  @PostMapping("/api/v2/members")
  public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
    Member member = new Member();
    member.setName(request.getName());

    Long id = memberService.join(member);
    return new CreateMemberResponse(id);
  }

  @PutMapping("/api/v2/members/{id}")
  public UpdateMemberResponse updateMemberV1(
      @PathVariable("id") Long id,
      @RequestBody @Valid UpdateMemberRequest request) {
    memberService.update(id, request.getName());
    Member findMember = memberService.findOne(id);
    return new UpdateMemberResponse(findMember.getId(), findMember.getName());
  }

  @GetMapping("/api/v1/members")
  public List<Member> membersV1() {
    return memberService.findMembers();
  }

  @GetMapping("/api/v2/members")
  public Result membersV2() {
    List<Member> findMembers = memberService.findMembers();
    List<MemberDto> collect = findMembers.stream()
        .map(m -> new MemberDto(m.getName()))
        .collect(Collectors.toList());
    return new Result(collect);
  }

  @Data
  @AllArgsConstructor
  private class Result<T> {

    private T data;
  }

  @Data
  @AllArgsConstructor
  static class MemberDto {

    private String name;
  }

  @Data
  @AllArgsConstructor
  static class CreateMemberRequest {

    @NotEmpty
    private String name;
  }

  @Data
  @AllArgsConstructor
  static class CreateMemberResponse {

    private Long id;
  }

  @Data
  @AllArgsConstructor
  static class UpdateMemberRequest {

    @NotEmpty
    private String name;
  }

  @Data
  @AllArgsConstructor
  static class UpdateMemberResponse {

    private Long id;
    private String name;
  }
}
