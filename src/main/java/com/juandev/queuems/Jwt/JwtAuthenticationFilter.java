package com.juandev.queuems.Jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import org.springframework.http.HttpHeaders;


public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String token = getTokenFromRequest(request);

        if (token==null) {
            filterChain.doFilter(request, response);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        //Obtener el encabezado que es donde se encuentra el token
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        //Validar si la palabra bearer esta en el encabezado
        if (StringUtils.hasText(authHeader) &&  authHeader.startsWith("Bearer ")){
            //Devuelve el token sin la palabra bearer
            return authHeader.substring(7);
        }
        return null;
    }
}
