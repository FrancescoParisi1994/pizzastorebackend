package it.prova.pizzastorebackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JWTFilter jwtFilter;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JWTAuthEntryPoint unauthorizedHandler;
	
	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception { 
		http.csrf().disable() // Disabling csrf
				.httpBasic().disable() // Disabling http basic
				.cors() // Enabling cors
				.and()
				
				.authorizeHttpRequests() 
				.antMatchers("/api/auth/login").permitAll()
				//tutti gli utenti autenticati possono richiedere le info
				.antMatchers("/api/utente/userInfo").authenticated()
				.antMatchers("/api/utente/**").hasRole("ADMIN")
				.antMatchers("/**").hasAnyRole("ADMIN", "CLASSIC_USER")
				// .antMatchers("/anonymous*").anonymous()
				.anyRequest().authenticated()
				.and()
				
				// imposto il mio custom user details service
				.userDetailsService(customUserDetailsService) 
				// quando qualcosa fallisce ho il mio handler che customizza la response
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
				.and()
				
				// non abbiamo bisogno di una sessione: meglio forzare a stateless
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); 

		// Adding the JWT filter
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}
