package com.yingxue.lesson.exception.handler;

import com.yingxue.lesson.exception.BusinessException;
import com.yingxue.lesson.exception.code.BaseResponseCode;
import com.yingxue.lesson.utils.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class test {

    @ExceptionHandler(value = Exception.class)
    public DataResult exception(Exception e){
        log.info("Exception...",e.getLocalizedMessage(),e);
        return DataResult.getResult(BaseResponseCode.SYSTEM_ERROR);
    }

    @ExceptionHandler(value = BusinessException.class)
    public DataResult businessException(BusinessException e){
        log.info("BUSINESSEXCEPTION....",e.getLocalizedMessage(),e);
        return DataResult.getResult(e.getCode(),e.getDefaultMessage());
    }



    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public DataResult validException(MethodArgumentNotValidException e){
        log.info("methodArgumentValidException...",e.getBindingResult().getAllErrors(),e);
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        return createValidExceptionResp(errors);
    }

    public DataResult createValidExceptionResp(List<ObjectError> errors){
        String str = "";
        String[] msg = new String[errors.size()];
        int i = 0 ;
        for(ObjectError error:errors){
            msg[i] = error.getDefaultMessage();
            log.info(msg[i]);
            i++;
        }
        return DataResult.getResult(BaseResponseCode.METHOD_IDENTITY_ERROR.getCode(),msg[i]);
    }


    @ExceptionHandler(value = UnauthorizedException.class)
    public DataResult unauthorizedException(UnauthorizedException e){
        log.info("UnauthorizedException....",e.getLocalizedMessage(),e);
        return DataResult.getResult(BaseResponseCode.NOT_PERMISSION);
    }
}
