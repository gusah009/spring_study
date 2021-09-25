package org.zerock.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TodoDTO2 {
    private String title;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date dueDate;
}
