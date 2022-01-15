package com.example.coding_dan.controller;

import com.example.coding_dan.dto.SampleDTO;
import com.example.coding_dan.dto.SampleDTOList;
import com.example.coding_dan.dto.TodoDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Controller도 안에 @Component를 포함하고 있어서 Scan할 때 알아서 찾아 집니다.
// 우린 jsp 안쓸거라서 RestController로 간드아..!
@RestController
@RequestMapping("/sample/*")
@Log4j2
public class SampleController {

  // RequestMapping은 원하는 Method(GET, POST, PUT, DELETE, ...)에 모두 맞춰 쓸 수 있다.
  @RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST})
  public void basicRequest() {
    log.info("basic .........");
  }

  @GetMapping("/basicOnlyGet")
  public void basicOnlyGet() {
    log.info("basic .........");
  }

  // Request Parameter를 DTO로 받을 수 있습니다!
  // http://localhost:8080/sample/ex02?name=AAA&age=10
  @GetMapping("/ex01")
  public String ex01(SampleDTO dto) {

    // 놀랍게도 SampleDTO의 @Data 어노테이션 덕분에 그냥 String으로 바뀐다..!
    log.info("" + dto);

    return "ex01 Success!";
  }

  // 물론 Request Parameter를 DTO로 받지 않고 직접 지정도 가능합니다!
  // http://localhost:8080/sample/ex02?name=AAA&age=10
  @GetMapping("/ex02")
  public String ex02(
      @RequestParam("name") String name,
      @RequestParam("age") int age) {

    log.info(name);
    log.info(age);

    return "ex02 Success!";
  }

  // 물론 Request Parameter를 DTO로 받지 않고 직접 지정도 가능합니다!
  // http://localhost:8080/sample/ex02List?ids=111&ids=222&ids=3
  @GetMapping("/ex02List")
  public String ex02List(
      @RequestParam("ids") ArrayList<String> ids) {

    log.info(ids);

    return "ex02List Success!";
  }

  // http://localhost:8080/sample/ex02Array?ids=111&ids=222&ids=3
  @GetMapping("/ex02Array")
  public String ex02Array(
      @RequestParam("ids") String[] ids) {

    log.info(Arrays.toString(ids));

    return "ex02Array Success!";
  }

  // http://localhost:8080/sample/ex02Bean?list%5B0%5D.name=hyeonmo&list%5B0%5D.age=25&list%5B1%5D.name=eunji&list%5B1%5D.age=22
  @GetMapping("/ex02Bean")
  public String ex02Bean(SampleDTOList list) {

    log.info("list DTOs: " + list);

    return "ex02Bean Success!";
  }

  // Controller로 들어오는 요청에 대해 다양한 설정이 가능
  // 지금 예시에선 년도형식으로 들어오는 String 데이터를 Date로 바꿔주고 있음.
  // InitBinder 외에 @DateTimeFormat 어노테이션을 DTO에 적용해서 변환이 가능함.
  @InitBinder
  public void initBinder(WebDataBinder binder) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
  }

  // @InitBinder를 쓸 경우:               http://localhost:8080/sample/ex03?title=test&dueDate=2022-01-15
  // ToDoDTO에 @DateTimeFormat을 쓸 경우: http://localhost:8080/sample/ex03?title=test&dueDate=2022/01/15
  // @InitBinder가 @DateTimeFormat보다 우선순위가 높아서 둘 다 적용시키면 @InitBinder가 적용됩니다.
  @GetMapping("/ex03")
  public String ex03(TodoDTO todo) {

    log.info("todo: " + todo);

    return "ex03 Success!";
  }
}
