package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

    private String statusCode;

    private String statusMsg;
}
