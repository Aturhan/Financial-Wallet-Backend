package com.currency.Backend.config;

import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@RequiredArgsConstructor
@Slf4j
public class JWTAuthFilter extends OncePerRequestFilter {

    private final AuthProvider authProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null){
            String[] authElement = header.split(" ");
            if (authElement.length == 2 && "Bearer".equals(authElement[0])){
                try {
                    SecurityContextHolder.getContext().setAuthentication(authProvider.validateToken(authElement[1]));
                }catch (TokenExpiredException e) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token Expired!");
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().write("Server error: " + e.getMessage());
                    log.error("Server error:", e);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
