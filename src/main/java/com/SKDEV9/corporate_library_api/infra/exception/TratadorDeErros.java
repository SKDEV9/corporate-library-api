package com.SKDEV9.corporate_library_api.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroMensagemDTO> tratarErroRegraDeNegocio(RuntimeException ex) {

        ErroMensagemDTO erro = new ErroMensagemDTO(ex.getMessage());

        return ResponseEntity.badRequest().body(erro);
    }
}
