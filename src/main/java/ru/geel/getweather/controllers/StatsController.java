package ru.geel.getweather.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.geel.getweather.jdbc.Operations;
import ru.geel.getweather.model.Stats;

import java.util.List;


/**
 * Created by ivangeel on 26.04.17.
 */

@RestController
public class StatsController {

    @Autowired
    Operations operations;

    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public ModelAndView getStats(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            List<Stats> statsList = operations.getStats(authentication.getName());
            model.addAttribute("stats", statsList);
            return new ModelAndView("stat", "user", authentication.getName());
        }

        return new ModelAndView("redirect:/");
    }
}
