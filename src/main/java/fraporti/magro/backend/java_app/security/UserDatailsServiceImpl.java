package fraporti.magro.backend.java_app.security;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fraporti.magro.backend.java_app.model.Permission;
import fraporti.magro.backend.java_app.model.User;
import fraporti.magro.backend.java_app.repository.UserRepository;

@Service
public class UserDatailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDatailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByUsername(username);

        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        User userData = user.get();

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(
            userData.getPermissions().stream()
                .map(Permission::getName)
                .collect(Collectors.toList())
                .toArray(String[]::new)
        );

        return new UserDatailsImpl(
            userData.getUsername(),
            userData.getPassword(),
            authorities,
            true,
            true,
            true,
            true
        );
    }
    
}
