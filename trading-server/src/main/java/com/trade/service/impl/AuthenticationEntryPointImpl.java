package com.trade.service.impl;

import com.alibaba.fastjson.JSON;
import com.trade.constant.MessageConstant;
import com.trade.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        Result<Object> result = Result.builder()
                .msg(MessageConstant.AUTHENTICATION_FAILURE)
                .code(401)
                .build();

        String json = JSON.toJSONString(result);

        try {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(json);
        } catch (IOException e) {
            log.error("json写出失败！");
        }
    }
}