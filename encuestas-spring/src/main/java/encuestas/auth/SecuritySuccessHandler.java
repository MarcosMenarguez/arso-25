package encuestas.auth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class SecuritySuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication) throws IOException {

        DefaultOAuth2User usuario = (DefaultOAuth2User) authentication.getPrincipal();

        Map<String, Object> claims = fetchUserInfo(usuario);

        if (claims != null) {
            // genera el token JWT y lo envía en la respuesta ...
            String token = JwtUtils.generateToken(claims);
            // escribe el token en response
            response.getWriter().println(token);
        }
    }

    private Map<String, Object> fetchUserInfo(DefaultOAuth2User usuario) {

        // TODO: buscar el usuario y retornar sus claims

        HashMap<String, Object> claims = new HashMap<String, Object>();
        claims.put("sub", usuario.getAttributes().get("login"));
        claims.put("roles", "ADMIN");

        return claims;
    }
}
