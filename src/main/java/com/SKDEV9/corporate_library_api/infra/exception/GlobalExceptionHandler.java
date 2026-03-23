package com.SKDEV9.corporate_library_api.infra.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErro400(MethodArgumentNotValidException ex) {

        List<DadosErroValidacao> erros = ex.getFieldErrors()
                .stream()
                .map(erro -> new DadosErroValidacao(erro.getField(), erro.getDefaultMessage()))
                .toList();


        return ResponseEntity.badRequest().body(erros);
    }


private record DadosErroValidacao(String campo, String erro) {}

}
