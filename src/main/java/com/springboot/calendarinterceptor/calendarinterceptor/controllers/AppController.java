package com.springboot.calendarinterceptor.calendarinterceptor.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppController {

  @GetMapping("/foo")
  public ResponseEntity<?> foo() {
    Map<String, Object> data = new HashMap<>();
    data.put("message", "Hello, World!");
    data.put("data", new Date());
    return ResponseEntity.status(HttpStatus.OK).body(data);
  }
}
