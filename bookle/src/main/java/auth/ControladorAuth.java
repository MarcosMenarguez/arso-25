package auth;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("auth")
public class ControladorAuth {

	// curl -X POST -H "Content-Type: application/x-www-form-urlencoded"  -d "username=juan&password=clave" http://localhost:8080/api/auth/login
	
	// eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWFuIiwicm9sZXMiOiJQUk9GRVNPUiIsImV4cCI6MTc0MDA2MTM2NX0.qRhl9H-5dd_kogZXwWZE5QcIf5fH-MyAX6X-DF-2B3U
	
	@POST
	@Path("/login")
	public Response login(@FormParam("username") String username, @FormParam("password") String password) {

		Map<String, Object> claims = verificarCredenciales(username, password);
		if (claims != null) {
			String token = JwtUtils.generateToken(claims);
			return Response.ok(token).build();
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciales inválidas").build();
		}

	}

	private Map<String, Object> verificarCredenciales(String username, String password) {
		
		// TODO: verificar las credenciales
		
		HashMap<String, Object> claims = new HashMap<String, Object>();
		claims.put("sub", username);
		claims.put("roles", "PROFESOR");
		
		return claims;
	}
}
