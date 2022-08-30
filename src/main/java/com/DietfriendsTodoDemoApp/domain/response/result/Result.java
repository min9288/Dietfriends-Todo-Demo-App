package com.DietfriendsTodoDemoApp.domain.response.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private boolean success;
    private int code;
    private String msg;
}
