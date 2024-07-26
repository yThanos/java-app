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
        System.out.println("Passing through UserDatailsServiceImpl");
        System.out.println("Username: " + username);
        Optional<User> user = this.userRepository.getUserByUsername(username);
        //Optional<User> user = Optional.of(new User(1L, "vitor@gmail.com",new BCryptPasswordEncoder().encode("1234"), "Vitor Fraporti", List.of(new Permission(1L, "USER", "User"))));
        System.out.println("Username after: " + username);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        User userData = user.get();
        System.out.println("User: " + userData);
        userData.setPermissions(List.of(new Permission(1L, "USER", "User")));

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
