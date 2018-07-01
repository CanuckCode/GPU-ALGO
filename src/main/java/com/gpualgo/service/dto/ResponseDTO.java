package com.gpualgo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class ResponseDTO {

    private Boolean success;
    private List<OverlockSettingDTO> content;
    private int totalElements;

}
