package com.example.coding_dan.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

// MapperScan 때 찾기 위해 Annotaion으로 등록합니다.
@Mapper
// interface로 작성합니다.
public interface TimeMapper {

  // MySQL 구문입니다. Oracle은 아래와 같이 하셔야 합니다.
  //  @Select("SELECT sysdate FROM dual;")
  @Select("SELECT curdate() FROM dual")
  public String getTimeWithSelectAnnotation(); // 현재 시간을 받아오는 쿼리문

  public String getTimeWithNoAnnotation(); // 현재 시간을 받아오는 쿼리문
}
