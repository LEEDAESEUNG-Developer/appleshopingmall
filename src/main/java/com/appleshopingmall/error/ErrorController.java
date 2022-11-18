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
    public String error(){
        return "/error/500";
    }
}
