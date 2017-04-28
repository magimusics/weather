package ru.geel.getweather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.net.URI;
import java.net.URISyntaxException;


/**
 * Created by ivangeel on 24.04.17.
 */

@SpringBootApplication
public class Application{

    public static void main(String[] args) throws URISyntaxException{
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

    }
}
