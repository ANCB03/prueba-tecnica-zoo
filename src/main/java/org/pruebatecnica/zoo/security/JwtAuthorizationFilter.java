package org.pruebatecnica.zoo.security;




import io.jsonwebtoken.ExpiredJwtException;
import org.pruebatecnica.zoo.implement.UsuarioImplement;
import org.pruebatecnica.zoo.util.Constants;
import org.pruebatecnica.zoo.util.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private UsuarioImplement userService;


	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
									FilterChain filterChain) throws ServletException, IOException {

		String authorizationHeader = httpServletRequest.getHeader(Constants.HEADER_AUTHORIZATION_KEY);

		if (StringUtils.isEmpty(authorizationHeader) || !authorizationHeader
				.startsWith(Constants.TOKEN_BEARER_PREFIX)) {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
			return;
		}
		final String token = authorizationHeader.replace(Constants.TOKEN_BEARER_PREFIX + " ", "");
		try {

			String userName = TokenProvider.getUserName(token);
			UserDetails user = userService.loadUserByUsername(userName);
			UsernamePasswordAuthenticationToken authenticationToken = TokenProvider.getAuthentication(token, user);
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		} catch (ExpiredJwtException ex) {
			SecurityContextHolder.clearContext();
			httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			httpServletResponse.setContentType("application/json");
			httpServletResponse.getWriter().write("{\"error\": \"Token JWT vencido o inválido\"}");
		}
	}
}
