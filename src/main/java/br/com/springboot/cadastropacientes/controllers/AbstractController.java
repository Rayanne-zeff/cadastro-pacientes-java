package br.com.springboot.cadastropacientes.controllers;

import br.com.springboot.cadastropacientes.response.Retorno;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável por conter tratamentos em comum entre todos os controladores
 * @author : Gloria Rayane
 * @since : 17/09/2021
 */

public abstract class AbstractController {

    /**
     * Função responsável por capturar erros na requisição e retornar de forma padronizada as mensagens
     * @param MethodArgumentNotValidException methodArgumentNotValidException
     * @return ResponseEntity<Object>
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException (MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> errors = new HashMap<>();

        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            errors.put(fieldName, message);
        });

        return Retorno.generateResponse("Bad request!", HttpStatus.BAD_REQUEST, errors);
    }

    /**
     * Função responsável por capturar validaçõs na requisição e retornar de forma padronizada as mensagens
     * @param InvalidFormatException invalidFormatException
     * @return ResponseEntity<Object>
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Object> handleValidationException (InvalidFormatException invalidFormatException) {
        Map<String, String> errors = new HashMap<>();

        invalidFormatException.getPath().forEach((error) -> {
            String fieldName = error.getFieldName();
            String message = "Valor informado inválido!";

            errors.put(fieldName, message);
        });

        return Retorno.generateResponse("Bad request!", HttpStatus.BAD_REQUEST, errors);
    }

}
