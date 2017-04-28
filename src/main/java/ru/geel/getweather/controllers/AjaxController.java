package ru.geel.getweather.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriTemplate;
import ru.geel.getweather.jdbc.Operations;
import ru.geel.getweather.model.Message;
import ru.geel.getweather.model.Weather;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ivangeel on 25.04.17.
 * 0,75006
 */

@RestController
public class AjaxController {

    private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID={key}";
    private static final String key = "47940e7e3e7ffae2afbcf0c469f6999d";
    private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);

    @Autowired
    Operations operations;

    @RequestMapping(value = "/ajax", method = RequestMethod.POST)
    public synchronized Weather ajaxGet(HttpServletRequest httpServletRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String cityName = httpServletRequest.getParameter("city");
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new UriTemplate(URL).expand(cityName, "", key);
        Weather weather = null;
        try {
            RequestEntity requestEntity = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
            ResponseEntity<Weather> responseEntity = restTemplate.exchange(requestEntity, Weather.class);

            weather = responseEntity.getBody();
            double d = new BigDecimal((double) (weather.getMain().get("temp")) - 273).setScale(2, RoundingMode.UP).doubleValue();

            weather.getMain().put("temp", d + "");

            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                System.err.println(authentication.getName());
                operations.addStat(weather, authentication.getName(), cityName, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }
        }
        catch (HttpClientErrorException e){
            System.err.println("Такого города не существует");
            logger.error("Такого города не существует", e);
        }

        return weather;
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Message addUser(HttpServletRequest httpServletRequest){
        String user = httpServletRequest.getParameter("username");
        String pass = httpServletRequest.getParameter("username");
        Message message = new Message();
        try {
            operations.addUser(user, pass);
        }
        catch (RuntimeException e){
            System.err.println("Пользователь "+user+" уже существует!");
            logger.error("Пользователь "+user+" уже существует!",e);
            message.setMessage("exist");
            return message;
        }
        message.setMessage("not");
        return message;
    }
}
