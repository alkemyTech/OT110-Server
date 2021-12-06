package com.alkemy.ong.security.jwt;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

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

@Component
@PropertySource("classpath:application.properties")
public class JwtProviderImpl implements IJwtProvider{

	@Value("${app.jwtSecret}")
	private static String JWT_SECRET;

	@Value("${app.jwtExpirationInMs}")
	private static Long JWT_EXPIRATION_TIME;
	
	
	
	@Override
	public Authentication getAuthentication(HttpServletRequest request) {
		Claims claims = extratClaims(request);
		if (claims == null) {
			return null;
		}
		String username = claims.getSubject();
		Long userId = claims.get("userId", Long.class);
		Set<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
				.map(SecurityUtils::convertToAuthority).collect(Collectors.toSet());
		UserDetails userDetails = UserDetailsImpl.builder().username(username).authorities(authorities).id(userId)
				.build();

		if (username == null) {
			return null;
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
	}

	@Override
	public boolean validateToken(HttpServletRequest request) {
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
		String token = SecurityUtils.extractAuthTokenFromRequest(request);
		if (token == null) {
			return null;
		}
		return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
	}



	@Override
	public String generateToken(UserDetailsImpl userDetails) {
		String authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		return Jwts.builder().setSubject(userDetails.getUsername()).claim("roles", authorities).claim("userId", ((UserDetailsImpl) userDetails).getId())
				.setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();

	}

	@Override
	public String generateToken(UserDetails userDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNombreUsuarioFromToken(String token){
        return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getSubject();
 }



	
	
}
