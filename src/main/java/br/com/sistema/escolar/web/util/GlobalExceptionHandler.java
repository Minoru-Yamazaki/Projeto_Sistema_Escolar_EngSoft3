package br.com.sistema.escolar.web.util;

import br.com.sistema.escolar.exceptions.BusinessException;
import br.com.sistema.escolar.web.domain.DefaultResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    public DefaultResponseMessage defaultResponseMessage(BusinessException ex){
        return DefaultResponseMessage.builder()
                                    .valid(false)
                                    .messages(ex.getMessages()).build();
    }
}
