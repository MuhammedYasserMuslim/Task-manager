package com.spring.exception.exceptions;

import com.spring.exception.model.BaseException;
import org.springframework.http.HttpStatus;

public class RecordNotFoundException extends BaseException {


    public RecordNotFoundException(String massage){
        super(massage);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
