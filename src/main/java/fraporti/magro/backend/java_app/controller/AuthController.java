package fraporti.magro.backend.java_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fraporti.magro.backend.java_app.model.User;
import fraporti.magro.backend.java_app.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return this.authService.login(user);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody User user) {
        return this.authService.create(user);
    }
}
