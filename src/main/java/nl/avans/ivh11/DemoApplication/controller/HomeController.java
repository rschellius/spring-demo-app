package nl.avans.ivh11.DemoApplication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    public String index(Model model) {
        logger.info("index method was called.");

        model.addAttribute("title", "Hier de titel");
        logger.debug("returning views/home/index.");
        return "views/home/index";
    }

}