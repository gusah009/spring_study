package org.zerock.controller;

import lombok.extern.log4j.Log4j;
import oracle.jdbc.proxy.annotation.Post;
import org.apache.ibatis.javassist.tools.rmi.Sample;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;
import org.zerock.domain.TodoDTO2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Controller
@Log4j
@RequestMapping("/sample/*")
public class SampleController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping("")
    public void basic() {
        log.info("basic..........");
    }

    @RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST})
    public void basicGet() {
        log.info("basic get..........");
    }

    @GetMapping("/basicOnlyGet")
    public void basicGet2() {
        log.info("basic get only get..........");
    }

    @GetMapping("/ex01")
    public String ex01(SampleDTO dto) {
        log.info(" " + dto);

        return "ex01";
    }

    @GetMapping("/ex02Bean")
    public String ex02(SampleDTOList list) {
        log.info("list dtos: " + list);

        return "ex02Bean";
    }

    @GetMapping("/ex03")
    public String ex03(TodoDTO todo) {
        log.info("todo: " + todo);

        return "ex03";
    }

    // InitBinder가 더 우선이다. 아래는 InitBinder가 있으면 적용되지 않는다.
    @GetMapping("/ex03-2")
    public String ex03_2(TodoDTO2 todo) {
        log.info("todo: " + todo);

        return "ex03_2";
    }

    // Model로 설정한 DTO class는 page단까지 넘어가지만 기본 자료형은 넘어가지 않는다.
    @GetMapping("/ex04")
//    public String ex04(SampleDTO dto, int page) {
    public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
        log.info("dto: " + dto);
        log.info("page: " + page);

        return "/sample/ex04";
    }

    @GetMapping(value = "/ex05", produces = "application/json; charset=utf8")
    public void ex05() {
        log.info("ex05");
    }

    @GetMapping(value = "/ex06", produces = "application/json; charset=utf8")
    public @ResponseBody SampleDTO ex06() {
        log.info("/ex06 ..............");

        SampleDTO dto = new SampleDTO();
        dto.setAge(10);
        dto.setName("핸모");
        log.info("핸모");
        log.info(dto.getName());

        return dto;
    }

    @GetMapping("/ex07")
    public ResponseEntity<String> ex07() {
        log.info("/ex07.........");
        String msg = "{\"name\": \"정핸모\"}";
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json;charset=UTF-8");
        return new ResponseEntity<>(msg, header, HttpStatus.OK);
    }

    @GetMapping("/exUpload")
    public void exUpload()
    {
        log.info("exUpload.........");
    }

    @PostMapping("/exUploadPost")
    public void exUploadPost(ArrayList<MultipartFile> files) {
        files.forEach(file -> {
            log.info("--------------");
            log.info("name: " + file.getOriginalFilename());
            log.info("size: " + file.getSize());
        });
    }
}
