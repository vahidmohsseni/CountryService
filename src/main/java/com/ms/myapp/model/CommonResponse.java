package com.ms.myapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;


@Getter
@JsonIgnoreProperties(value ={"name", "country_code", "capital", "population", "flag_file_url"})
public class CommonResponse extends Country{

    public CommonResponse(String message, int code){
        super();
        this.message = message;
        this.code = code;

    }

    private final String message;
    private final int code;

    @Override
    public String toString(){
        return String.format("{message:%s, code:%s}", this.message, this.code);
    }

}
