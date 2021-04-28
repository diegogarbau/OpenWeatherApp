package com.example.weatherApp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "Error")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDTO {
    private String msg;

    public ErrorDTO(String msg) {
        this.msg = msg;
    }

    @JsonProperty(value = "Mensaje de error")
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

