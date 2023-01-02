package it.prova.pizzastorebackend.security.auth;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pizzastorebackend.security.CustomAuthenticationManager;
import it.prova.pizzastorebackend.security.JWTUtil;
import it.prova.pizzastorebackend.security.dto.UtenteAuthDTO;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class AuthController {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private CustomAuthenticationManager authManager;
	
	@PostMapping("/login")
	public Map<String, Object> loginHandler(@RequestBody UtenteAuthDTO body) throws Exception {
		try {
			// Creating the Authentication Token which will contain the credentials for
			// authenticating
			// This token is used as input to the authentication process
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
					body.getUsername(), body.getPassword());

			// Authenticating the Login Credentials
			authManager.authenticate(authInputToken);

			// Se siamo qui posso tranquillamente generare il JWT Token
			String token = jwtUtil.generateToken(body.getUsername());

			// Respond with the JWT
			return Collections.singletonMap("jwt-token", token);
		} catch (AuthenticationException authExc) {
			// Auhentication Failed
			throw new RuntimeException("Invalid Login Credentials");
		}
	}

}
