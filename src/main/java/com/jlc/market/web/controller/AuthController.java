package com.jlc.market.web.controller;

import com.jlc.market.domain.dto.AuthenticationRequest;
import com.jlc.market.domain.dto.AuthenticationResponse;
import com.jlc.market.domain.service.MarketUserDetailsService;
import com.jlc.market.web.security.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Api(tags = "Authenticate")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private MarketUserDetailsService marketUserDetailsService;
    private JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, MarketUserDetailsService marketUserDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.marketUserDetailsService = marketUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/authenticate")
    @ApiOperation(value = "Authenticate with API", notes = "Resource to get token authenticated")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Authenticated - Token created"),
            @ApiResponse(code = 401, message = "Expired token"),
            @ApiResponse(code = 403, message = "Request not valid"),
    })
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            UserDetails userDetails = marketUserDetailsService.loadUserByUsername(request.getUsername());

            String jwt = jwtUtil.generateToken(userDetails);

            return new ResponseEntity<>(new AuthenticationResponse(jwt, "Authenticated"), HttpStatus.CREATED);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AuthenticationResponse(null, "Bad Credentials"), HttpStatus.FORBIDDEN);
        } catch (ExpiredJwtException e) {
            return new ResponseEntity<>(new AuthenticationResponse(null, "Expired token"), HttpStatus.UNAUTHORIZED);
        }
    }
}