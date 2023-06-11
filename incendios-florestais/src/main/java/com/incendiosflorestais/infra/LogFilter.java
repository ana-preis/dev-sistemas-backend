package com.incendiosflorestais.infra;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
@Order(-999)
public class LogFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws IOException, ServletException {
        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper resp = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(req, resp);

        String responseBody = new String(resp.getContentAsByteArray(), StandardCharsets.UTF_8);
        String requestBody = new String(req.getContentAsByteArray(), StandardCharsets.UTF_8);

        log.info("Request\n METHOD: {} | URI: {} | PORT: {}", req.getMethod(), req.getRequestURI(), req.getLocalPort());

        log.info("Response\n STATUS: {}\n BODY:{}", resp.getStatus(), responseBody);
        log.info("Response BODY: {}", requestBody);
        resp.copyBodyToResponse();
    }
}
