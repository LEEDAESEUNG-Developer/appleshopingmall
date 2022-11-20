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

    @RequestMapping("redirect-errorPage-404")
    public String redirectErrorPage404(){
        return "redirect:/error/404";
    }

    // 세션이 없는 사람 리다이락테
    @RequestMapping("redirect-session-error")
    public String redirectSessionError(){
        return "redirect:/member/login";
    }

}
