package vn.edu.iuh.fit.auth_jwt.filter;

import vn.edu.iuh.fit.auth_jwt.authen.UserPrincipal;
import vn.edu.iuh.fit.auth_jwt.entity.Token;
import vn.edu.iuh.fit.auth_jwt.service.TokenService;
import vn.edu.iuh.fit.auth_jwt.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenService verificationTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authorizationHeader
                = request.getHeader("Authorization");
        System.out.println(authorizationHeader);

        UserPrincipal user = null;
        Token token = null;


        if (StringUtils.hasText(authorizationHeader) &&
//                authorizationHeader.startsWith("Token ")) {
            authorizationHeader.startsWith("Bearer ")) {
            System.out.println("running1");
            String jwt = authorizationHeader.substring(7);

            user = jwtUtil.getUserFromToken(jwt);
            token = verificationTokenService.findByToken(jwt);
        }

        if (null != user && null != token && token.getTokenExpDate().after(new Date())) {
            System.out.println("running2");
            Set<GrantedAuthority> authorities = new HashSet<>();

            user.getAuthorities().forEach(
                    p -> authorities.add(new SimpleGrantedAuthority((String) p)));

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user, null, authorities);

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
        System.out.println("running");
    }
}
