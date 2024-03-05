package com.jesus.curso.springboot.calendar.interceptor.springboothorario.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;


@RestController
public class AppController {

    @GetMapping("/foo")
    public ResponseEntity<?> foo(HttpServletRequest request){  //con httpservletreques le compartimos el msj del calendar (request)
        Map<String, Object> data = new HashMap<>();
        data.put("title", "Bienvenidos al sistema de atencion");
        data.put("time", new Date());
        data.put("message", request.getAttribute("message"));  //con put le agregamos al map el request creado con getattribute y le pasamos el mismo nombre del mensaje
        return ResponseEntity.ok(data);
    }
}
