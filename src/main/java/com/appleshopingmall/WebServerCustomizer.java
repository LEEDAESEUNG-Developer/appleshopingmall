package com.appleshopingmall;

import com.appleshopingmall.error.exception.AdminSessionException;
import com.appleshopingmall.error.exception.EmailSessionException;
import com.appleshopingmall.error.exception.MemberSessionException;
import com.appleshopingmall.error.exception.ProductStockErrorException;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        ErrorPage error500 = new ErrorPage(ProductStockErrorException.class, "/error/redirect-errorPage-500");
        ErrorPage memberSessionError = new ErrorPage(MemberSessionException.class, "/error/redirect-session-error");
        ErrorPage adminSessionError = new ErrorPage(AdminSessionException.class, "/error/404");
        ErrorPage emailSessionError = new ErrorPage(EmailSessionException.class, "/error/redirect-email-error");


        factory.addErrorPages(error500, memberSessionError, adminSessionError, emailSessionError);
    }
}
