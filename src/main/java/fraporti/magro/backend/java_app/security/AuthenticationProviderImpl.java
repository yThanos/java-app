package fraporti.magro.backend.java_app.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {
    private final UserDatailsServiceImpl userDatailsServiceImpl;

    public AuthenticationProviderImpl(UserDatailsServiceImpl userDatailsServiceImpl) {
        this.userDatailsServiceImpl = userDatailsServiceImpl;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("Passing through AuthenticationProviderImpl");
        final String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
        final String password = (authentication.getCredentials() == null) ? "NONE_PROVIDED" : authentication.getCredentials().toString();

        if (username.equals("NONE_PROVIDED") || password.equals("NONE_PROVIDED")) {
            throw new BadCredentialsException("Nenhum login ou senha foi informado!");
        }

        System.out.println("Username: "+username);
        System.out.println("Password: "+password);

        UserDetails userDatails = this.userDatailsServiceImpl.loadUserByUsername(username);

        System.out.println("Details: "+userDatails);

        if(new BCryptPasswordEncoder().matches(password, userDatails.getPassword())){
            System.out.println("Usuário autenticado!");
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDatails.getUsername(), userDatails.getPassword(), userDatails.getAuthorities());
            auth.setDetails(userDatails);
            return auth;
        }
        throw new BadCredentialsException("Usuário ou senha inválidos!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
    
}
