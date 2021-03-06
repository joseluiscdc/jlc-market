package com.jlc.market.web.security.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jlc.market.domain.service.MarketUserDetailsService;
import com.jlc.market.web.security.JwtUtil;

import java.io.IOException;

@Component
public class JwtFilterRequest extends OncePerRequestFilter {
    private static final int SPACE_BEARER = 7;
    private JwtUtil jwtUtil;
    private MarketUserDetailsService marketUserDetailsService;

    public JwtFilterRequest(JwtUtil jwtUtil, MarketUserDetailsService marketUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.marketUserDetailsService = marketUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            String jwt = authorizationHeader.substring(SPACE_BEARER);
            String username = jwtUtil.extractUsername(jwt);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = marketUserDetailsService.loadUserByUsername(username);

                if (jwtUtil.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                            null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
