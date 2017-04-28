package ru.geel.getweather.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.geel.getweather.jdbc.Operations;

/**
 * Created by ivangeel on 26.04.17.
 */

@Controller
public class MainController {

    @Autowired
    Operations operations;

    @RequestMapping("/")
    public ModelAndView getMain(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            String usr = operations.getUser(authentication.getName());
            return new ModelAndView("index", "user", usr);
        }
        return new ModelAndView("index");
    }
}
