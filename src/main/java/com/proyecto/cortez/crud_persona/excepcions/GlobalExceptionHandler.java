package com.proyecto.cortez.crud_persona.excepcions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(PersonaNoEncontradaException.class)
    public ResponseEntity<Map<String,Object>> handlePersonaNoEncontrada(PersonaNoEncontradaException ex){
        Map<String,Object> body=new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status",HttpStatus.NOT_FOUND.value());
        body.put("error","Not found");
        body.put("mensaje", ex.getMessage());

        return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
    }

    //errores del sistema
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleGeneral(Exception ex){
        Map<String,Object> body=new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error","Internal Server error");
        body.put("mensaje", ex.getMessage());

        return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String,Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex){
        Map<String,Object> body=new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status",HttpStatus.BAD_REQUEST.value());
        body.put("error","Bad request");

        String mensaje="Error de intregridad de datos";
        if(ex.getMessage().contains("UK")){

            mensaje="El correo ya existe";
        }
        body.put("mensaje", mensaje);

        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String,Object> body=new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status",HttpStatus.BAD_REQUEST.value());
        body.put("error","Bad request");

        List<String> errores=ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(FieldError::getDefaultMessage)
        .toList();

        body.put("mensaje", errores);

        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
    }




    
}
