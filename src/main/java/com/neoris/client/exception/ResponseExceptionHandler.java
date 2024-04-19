package com.neoris.client.exception;

import com.neoris.vo.BaseResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<BaseResponseVo> manejarTodasExcepciones(Exception ex, WebRequest request){
        log.error(ex.getMessage());
        BaseResponseVo er = BaseResponseVo.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage()).build();
        return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<BaseResponseVo> manejarModeloNotFoundException(ModelNotFoundException ex, WebRequest request){
        log.error(ex.getMessage());
        BaseResponseVo er = BaseResponseVo.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage()).build();
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String message = ex.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage().concat(". ")).collect(Collectors.joining());
        log.error(message);
        BaseResponseVo er = BaseResponseVo.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(message).build();
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }

}
