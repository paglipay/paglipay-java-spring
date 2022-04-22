package info.paglipay.springdemo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import info.paglipay.springdemo.services.AuthService;

//import info.paglipay.springdemo.services.AuthService;

@Configurable
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthService authService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Enable CORS and disable CSRF
		http.csrf().disable();
//	    http.sessionManagement().sessionCreationPolicy('STATELESS');

		// Set session management to stateless
		http.authorizeRequests().anyRequest().permitAll();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}