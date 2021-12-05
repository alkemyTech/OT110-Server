package com.alkemy.ong.security.jwt;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.alkemy.ong.security.service.UserDetailsImpl;
import com.alkemy.ong.util.SecurityUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Log4j2
@Component
@PropertySource("classpath:application.properties")
public class JwtProviderImpl implements IJwtProvider {

//	@Value("${app.jwtSecret}")
	private static String JWT_SECRET = "C9D0629998B86CE363D1885709757E9F29297B912F66BE3A019BDD74C15D3F03";

//	@Value("${app.jwtExpirationInMs}")
	private static Long JWT_EXPIRATION_TIME = Long.valueOf(3600000);

	@Override
	public String generateToken(UserDetailsImpl auth) {
		log.info("[JwtProviderImpl] ->  generateToken()");
		log.info("[JwtProviderImpl] ->  authorities");
		String authorities = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

        log.info("JWT_SECRET " + JWT_SECRET);
        log.info("JWT_EXPIRATION_TIME " + JWT_EXPIRATION_TIME);

        String token = Jwts.builder().setSubject(auth.getUsername()).claim("roles", authorities).claim("userId", auth.getId())
				.setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
        log.info("[JwtProviderImpl] ->  Return Token " + token);
		return token;

	}

	@Override
	public Authentication getAuthentication(HttpServletRequest request) {
		log.info("[JwtProviderImpl] ->  getAuthentication()");
		Claims claims = extratClaims(request);
		if (claims == null) {
			return null;
		}
		log.info("[JwtProviderImpl] ->  Data For (UserDetails)");
		String username = claims.getSubject();
		Long userId = claims.get("userId", Long.class);
		Set<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
				.map(SecurityUtils::convertToAuthority).collect(Collectors.toSet());
		log.info("[JwtProviderImpl] ->  Build UserDetailsImpl");
		UserDetails userDetails = UserDetailsImpl.builder().username(username).authorities(authorities).id(userId)
				.build();

		if (username == null) {
			return null;
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
	}

	@Override
	public boolean validateToken(HttpServletRequest request) {
		log.info("[JwtProviderImpl] -> validateToken()");
		Claims claims = extratClaims(request);
		if (claims == null) {
			return false;
		}
		if (claims.getExpiration().before(new Date())) {
			return false;
		}
		return true;
	}

	private Claims extratClaims(HttpServletRequest request) {
		log.info("[JwtProviderImpl] -> extratClaims()");
		log.info("[JwtProviderImpl] -> SecurityUtils.extractAuthTokenFromRequest()");
		String token = SecurityUtils.extractAuthTokenFromRequest(request);
		if (token == null) {
			return null;
		}
		return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
	}

}

