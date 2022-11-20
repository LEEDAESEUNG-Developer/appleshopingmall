package com.appleshopingmall.error;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("error")
public class ErrorController {

    @RequestMapping("500")
    public String error500(){
        return "/error/500";
    }

    @RequestMapping("404")
    public String error404() { return "/error/404"; }
}
