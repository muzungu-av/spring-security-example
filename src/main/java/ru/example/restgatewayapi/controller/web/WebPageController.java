package ru.example.restgatewayapi.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebPageController {

    @RequestMapping("/static")
    public String publicPage() {
        return "static.html";
    }

    @RequestMapping("/private")
    public String privatePage() {
        return "/private/private.html";
    }

}
