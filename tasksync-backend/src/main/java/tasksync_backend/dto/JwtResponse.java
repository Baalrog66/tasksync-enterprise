package tasksync_backend.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String email;

    public JwtResponse(String token, String email) {
        this.token = token;
        this.email = email;
    }
}