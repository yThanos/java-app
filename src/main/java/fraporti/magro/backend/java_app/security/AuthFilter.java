package fraporti.magro.backend.java_app.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import fraporti.magro.backend.java_app.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter{
    private final UserDatailsServiceImpl userDatailsServiceImpl;

    public AuthFilter(UserDatailsServiceImpl userDatailsServiceImpl) {
        this.userDatailsServiceImpl = userDatailsServiceImpl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Passing through AuthFilter");
        String authorization = request.getHeader("Authorization");
        String username = JwtUtil.getUsernameToken(authorization);
        System.out.println(request.getRequestURI());
        if(request.getRequestURI().contains("/auth") || request.getRequestURI().contains("/book/byRating")){
            filterChain.doFilter(request, response);
            return;
        }
        if (authorization == null) {
            System.out.println("Sem token");
            //response.sendError(401);
            filterChain.doFilter(request, response);
            return;
        } else if(JwtUtil.isTokenExpired(authorization)){
            System.out.println("Token expirado");
            response.sendError(419);
            return;
        } else {
            if(SecurityContextHolder.getContext().getAuthentication() == null){
                System.out.println("Token válido");
                UserDetails user = userDatailsServiceImpl.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println("Auth: "+auth);
            }
        }
        filterChain.doFilter(request, response);
    }
    
}
