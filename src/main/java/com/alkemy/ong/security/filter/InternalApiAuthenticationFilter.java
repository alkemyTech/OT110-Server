package com.alkemy.ong.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alkemy.ong.security.service.UserDetailsImpl;
import com.alkemy.ong.security.util.SecurityUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InternalApiAuthenticationFilter extends OncePerRequestFilter {

    private final String accessKey;

    public InternalApiAuthenticationFilter(String accessKey) {
        this.accessKey = accessKey;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getRequestURI().startsWith("/api/v1/internal");
    }	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
        try {
            String requestKey = SecurityUtils.extractAuthTokenFromRequest(request);
            if (requestKey == null || !requestKey.equals(accessKey)){
                log.warn("Internal Key Endpoint Requested Without/Wrong Key URI: {}", request.getRequestURI());
                throw  new RuntimeException("UNAUTHORIZED");
            }
            UserDetailsImpl user = UserDetailsImpl.createSuperUser();
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (Exception e){
            log.error("Could Not Set User Authentication In Security Context", e);
        }
        filterChain.doFilter(request, response);		
	}

}
