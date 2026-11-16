package tasksync_backend.controller;

import tasksync_backend.dto.JwtResponse;
import tasksync_backend.dto.LoginRequest;
import tasksync_backend.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        // Autenticamos al usuario con Spring Security
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        // Si es exitoso, lo establecemos en el contexto
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generamos el token JWT
        String jwt = jwtUtils.generateToken(loginRequest.getEmail());

        // Retornamos la respuesta con el token
        return ResponseEntity.ok(new JwtResponse(jwt, loginRequest.getEmail()));
    }
}
