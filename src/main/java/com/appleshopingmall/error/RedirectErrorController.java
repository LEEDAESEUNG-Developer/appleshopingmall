package com.appleshopingmall.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("error")
public class RedirectErrorController {

    @RequestMapping("redirect-errorPage-500")
    public String redirectErrorPage500(){
        return "redirect:/error/500";
    }

}
