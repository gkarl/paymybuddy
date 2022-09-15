package com.payMyBuddy.pay.config;

import com.payMyBuddy.pay.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestResponseLoggingFilter implements Filter {



    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Logger logger = LoggerFactory.getLogger(UserController.class);

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        logger.info("Logging Request  {} : {} : {}", req.getMethod(), req.getRequestURI(), req.getQueryString());
        chain.doFilter(request, response);

        logger.info("Logging Response {} : {}", res.getStatus(), res.getContentType());
    }

}