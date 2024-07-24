package fraporti.magro.backend.java_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fraporti.magro.backend.java_app.dto.UserDTO;
import fraporti.magro.backend.java_app.model.Permission;
import fraporti.magro.backend.java_app.model.User;
import fraporti.magro.backend.java_app.repository.UserRepository;
import fraporti.magro.backend.java_app.util.JwtUtil;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<?> login(User user) {
        System.out.println("Login: "+user.getUsername());
        try{
            final Authentication authentication = this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            System.out.println("auth: "+authentication);
            if(authentication.isAuthenticated()){
                user = this.userRepository.findByUsername(user.getUsername()).orElseThrow();
                String token = JwtUtil.generateToken(user.getUsername());
                UserDTO userDTO = new UserDTO(user.getIdUser(), user.getUsername(), user.getName(), token);
                return new ResponseEntity<>(userDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return new ResponseEntity<>("Credenciais inválidas!", HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<?> create(User user) {
        Optional<User> usuario = this.userRepository.findByUsername(user.getUsername());
        if(usuario.isPresent()){
            return new ResponseEntity<>("Já existe uma conta cadastrada para esse email!", HttpStatus.UNAUTHORIZED);
        }
        System.out.println("Create: "+user.getUsername());
        try {
            Permission permission = new Permission();
            permission.setIdPermission(1L);
            permission.setDescription("USER");
            user.setPermissions(List.of(permission));
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user = this.userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
