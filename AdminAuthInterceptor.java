
package com.artforyou.store.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        String method = request.getMethod();
        String uri = request.getRequestURI();

        // Allow CORS preflight requests
        if ("OPTIONS".equalsIgnoreCase(method)) {
            return true;
        }

        // Allow customers to place orders
        if ("POST".equalsIgnoreCase(method)
                && "/api/orders".equals(uri)) {
            return true;
        }

        // Allow customers to upload payment screenshots
        if ("POST".equalsIgnoreCase(method)
                && uri.matches(".*/api/orders/\\d+/payment-proof")) {
            return true;
        }
     // Allow customers to view their own orders
        if ("GET".equalsIgnoreCase(method)
                && "/api/orders/my-orders".equals(uri)) {
            return true;
        }

        // Check admin login for all other protected requests
        HttpSession session = request.getSession(false);

        if (session != null &&
                Boolean.TRUE.equals(session.getAttribute("adminLoggedIn"))) {
            return true;
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        response.getWriter().write(
            "{\"message\":\"Please login as admin\"}"
        );

        return false;
    }
}
