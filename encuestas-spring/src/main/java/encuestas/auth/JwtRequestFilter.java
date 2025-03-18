package encuestas.auth;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        // Comprueba que la petición lleve el token JWT y lo valida
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {

            try {
                String token = authorization.substring("Bearer ".length()).trim();
                Claims claims = JwtUtils.validateToken(token);
                String[] roles = claims.get("roles", String.class).split(",");

                // Establece el contexto de seguridad
                ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                for (String rol : roles)
                    authorities.add(new SimpleGrantedAuthority(rol));

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(),
                        null, authorities);
                // Establecemos la autenticación en el contexto de seguridad
                // Se interpreta como que el usuario ha superado la autenticación
                SecurityContextHolder.getContext().setAuthentication(auth);
                
            } catch (Exception e) {
            	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "El token JWT no es correcto");  
            	return;
            }
        }
        
        chain.doFilter(request, response);
        
    }
}
