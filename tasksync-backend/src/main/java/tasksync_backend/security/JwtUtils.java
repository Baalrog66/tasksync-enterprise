package tasksync_backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    // Clave secreta para firmar el token (en un entorno real va en application.properties o variables de entorno)
    private final String jwtSecret = "TaskSyncEnterpriseSuperSecretKeyForJWTAuthentication2026Security";
    private final int jwtExpirationMs = 86400000; // 1 día en milisegundos

    private SecretKey key() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    // Generar el token JWT a partir del nombre de usuario / email
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key())
                .compact();
    }

    // Obtener el usuario a partir del token
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // Validar el token
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().verifyWith(key()).build().parse(authToken);
            return true;
        } catch (Exception e) {
            System.err.println("Error de validación del token JWT: " + e.getMessage());
        }
        return false;
    }
}
