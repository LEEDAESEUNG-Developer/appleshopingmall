package com.appleshopingmall;

import com.appleshopingmall.error.exception.AdminSession;
import com.appleshopingmall.error.exception.EmailSessionExcepiton;
import com.appleshopingmall.error.exception.MemberSession;
import com.appleshopingmall.error.exception.ProductStockError;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        ErrorPage error500 = new ErrorPage(ProductStockError.class, "/error/redirect-errorPage-500");
        ErrorPage memberSessionError = new ErrorPage(MemberSession.class, "/error/redirect-session-error");
        ErrorPage adminSessionError = new ErrorPage(AdminSession.class, "/error/404");
        ErrorPage emailSessionError = new ErrorPage(EmailSessionExcepiton.class, "/error/redirect-email-error");


        factory.addErrorPages(error500, memberSessionError, adminSessionError, emailSessionError);
    }
}
