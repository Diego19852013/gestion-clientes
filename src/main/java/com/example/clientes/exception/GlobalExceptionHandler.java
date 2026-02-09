package com.example.clientes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 🔴 Errores de validación (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarValidaciones(
            MethodArgumentNotValidException ex) {

        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        });

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("status", 400);
        respuesta.put("errores", errores);

        return ResponseEntity.badRequest().body(respuesta);
    }

    // 🔴 Errores generales (RuntimeException)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> manejarRuntime(RuntimeException ex) {

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("status", 400);
        respuesta.put("mensaje", ex.getMessage());

        return ResponseEntity.badRequest().body(respuesta);
    }
}
