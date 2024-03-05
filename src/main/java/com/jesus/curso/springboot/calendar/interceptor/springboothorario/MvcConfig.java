package com.jesus.curso.springboot.calendar.interceptor.springboothorario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{  //CREAMOS LA CLASE CNFIGURACION PARA PODER REGISTRAR EL INTERCEPTOR

    @Autowired
    @Qualifier("calendarInterceptor") //le indicamos cual ocupara
    private HandlerInterceptor calendar;  //INYECTAMOS EL ATRIBUTO DEL TIPO HANDLERINTERCEPTOR


    //SOBREESCRIBIMOS EL METODO ADDINTERCEPTOR
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(calendar).addPathPatterns("/foo");  //se registra el calendar y le decimos que solo se aplique al ednpoint foo
    }

}
