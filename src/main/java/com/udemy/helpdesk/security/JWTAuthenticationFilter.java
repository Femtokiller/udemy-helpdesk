package com.udemy.helpdesk.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.udemy.helpdesk.domain.dtos.CredenciaisDTO;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	
	private final JWTUtil jwtUtil;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil util) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtUtil = util;
	}	
	
	@Override
		public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
				throws AuthenticationException {
			try
			{
				CredenciaisDTO credenciais = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDTO.class);
				UsernamePasswordAuthenticationToken autenticationToken = new UsernamePasswordAuthenticationToken(credenciais.getEmail(), credenciais.getSenha(), new ArrayList<>());
				Authentication authentication = authenticationManager.authenticate(autenticationToken);
				return authentication;
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
	
	@Override
		protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
				Authentication authResult) throws IOException, ServletException {
			String username = ((UserSS) authResult.getPrincipal()).getUsername();
			String token = jwtUtil.generateToken(username);
			
			response.setHeader("access-control-expose-headers", "Authorization");
			response.setHeader("Authorization", "Bearer " + token);
		}
	
	@Override
		protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException failed) throws IOException, ServletException {
			response.setStatus(401);
			response.setContentType("Application/json");
			response.getWriter().append(json());
		}
	
	private CharSequence json()
	{
		long date = System.currentTimeMillis();
		
		return "{"
				+ "\"timestamp\": " + date + ", "
				+ "\"satus\": 401, "
				+ "\"error\": \"Não autorizado\", "
				+ "\"message\": \"Email ou senha inválidos\", "
				+ "\"path\": \"/login\"}";
	}

}
