package com.jesus.curso.springboot.calendar.interceptor.springboothorario.interceptors;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("calendarInterceptor")  //le damos un nombre para indiciarle la instancia que se inyectara en el mvcconfig
public class CalendarInterceptor implements HandlerInterceptor{

    @Value("${config.calendar.open}")
    private Integer open;

    @Value("${config.calendar.close}")
    private Integer close;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
                Calendar calendar = Calendar.getInstance(); //obtendremos la hora con calendar para obtener una instancia
                int hour = calendar.get(Calendar.HOUR_OF_DAY); //poiendo un variable int hour con get obetenmos la hora y le pasamos como parametre Calendar.Hour

                System.out.println(hour); //imprimimos la hora actual
                //se crea una condicion en la cual si el horario esta dentro del rango acceda al ednpoint
                if(hour >= open && hour <= close){
                    StringBuilder message = new StringBuilder("Bienvenidos al horario de atenciona clientes");
                    message.append(", atendemos desde las ");
                    message.append(open);
                    message.append("hrs. ");
                    message.append("hasta las ");
                    message.append(close);
                    message.append(" hrs");
                    message.append(" Gracias por su visita");
                    request.setAttribute("message", message.toString());  //lo pasamos con un request y lo convertimos de stringbuilder a un string para pasarlo al endpoint
                    return true;
                }
                
                //regresamos un json en caso de false con objectmaper
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> data = new HashMap<>(); // creaemos un map para pasar los datos del tipo string
                StringBuilder message = new StringBuilder("Cerrado, fuera del horario de atencion ");  //creamos un string builder 
                message.append("por favor visitenos desde las ");  //concatenamos con append
                message.append(open);
                message.append(" hasta las ");
                message.append(close);
                message.append(" hrs. gracias");
                data.put("message", message.toString());
                data.put("date", new Date().toString());

                response.setContentType("application/json"); //convertimos a json y poder pasarlo a la respuesta o end point
                response.setStatus(401);  //mandamos el status
                response.getWriter().write(mapper.writeValueAsString(data)); //pasamos el maper para convertirlo a un json y escirbir el valor como un string
                return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
    }
    
}
