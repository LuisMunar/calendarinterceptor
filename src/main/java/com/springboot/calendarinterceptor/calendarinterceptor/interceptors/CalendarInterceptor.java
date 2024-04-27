package com.springboot.calendarinterceptor.calendarinterceptor.interceptors;

import java.util.Calendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CalendarInterceptor implements HandlerInterceptor {

  @Value("${config.calendar.open}")
  private Integer openHour;

  @Value("${config.calendar.close}")
  private Integer closeHour;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    Calendar calendar = Calendar.getInstance();
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    System.out.println("Hour: " + hour);

    if (hour < openHour || hour > closeHour) {
      Map<String, Object> model = Map.of("message", "The service is not available at the moment. Please try again later.");
      
      ObjectMapper objectMapper = new ObjectMapper();
      String jsonString = objectMapper.writeValueAsString(model);

      response.setContentType("application/json");
      response.setStatus(HttpStatus.LOCKED.value());
      response.getWriter().write(jsonString);
      return false;
    }

    return true;
  }

  @Override
  public void postHandle(
    HttpServletRequest request,
    HttpServletResponse response,
    Object handler,
    ModelAndView modelAndView
  ) throws Exception {
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }
}
