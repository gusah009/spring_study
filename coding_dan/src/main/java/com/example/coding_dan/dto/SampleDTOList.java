package com.example.coding_dan.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class SampleDTOList {

  private List<SampleDTO> list;

  SampleDTOList() {
    list = new ArrayList<>();
  }
}
