package encuestas.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class ControllerAuth {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {

        Map<String, Object> claims = verificarCredenciales(username, password);
        if (claims != null) {
            String token = JwtUtils.generateToken(claims);
            return ResponseEntity.ok(token);
        } else {
            // retorna código unauthorized y un mensaje de error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }

    }
    

    private Map<String, Object> verificarCredenciales(String username, String password) {

        // TODO: verificar las credenciales

        HashMap<String, Object> claims = new HashMap<String, Object>();
        claims.put("sub", username);
        claims.put("roles", "ADMIN");

        return claims;
    }
}
