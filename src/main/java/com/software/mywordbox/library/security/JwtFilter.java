package com.software.mywordbox.library.security;

import com.software.mywordbox.library.enums.MessageCodes;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
* Amaç: Gelen token'ı doğrulamak ve kimlik bilgilerini Spring Security'ye entegre etmek.
*/

@Service
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailService customUserDetailService;
    private static final String AUTHORIZATION = "Authorization";
    private static final String JWT_BEARER = "Bearer ";


    public JwtFilter(JwtUtil jwtUtil, CustomUserDetailService customUserDetailService) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailService = customUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            logger.info("istek düştü");
            logger.info(request.getRequestURI());
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if (request.getRequestURI().startsWith("/api/auth/")) {
                filterChain.doFilter(request, response);
                return;
            }
            if (authorizationHeader != null && authorizationHeader.startsWith(JWT_BEARER)) {
                String jwt = authorizationHeader.substring(7);
                String username = jwtUtil.extractUsername(jwt);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

                    if (jwtUtil.validateToken(jwt, userDetails)) {
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            }
            filterChain.doFilter(request, response);

        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            sendErrorResponse(response, MessageCodes.TOKEN_EXPIRED);
        } catch (Exception e) {
            sendErrorResponse(response, MessageCodes.FAIL);
        }
    }

    private void sendErrorResponse(HttpServletResponse response, MessageCodes messageCode) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");

        String jsonResponse = String.format(
                "{\"data\": null, \"meta\": {\"description\": \"%s\", \"code\": \"%s\"}}",
                messageCode.getMessage(), // Örn: "general.tokenExpired"
                messageCode.getCode()      // Örn: "1025"
        );

        response.getWriter().write(jsonResponse);
    }
}
