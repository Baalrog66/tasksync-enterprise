package tasksync_backend.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;    // O username, dependiendo de cómo guardes el identificador
    private String password;
}
