package com.example.Test.config;

import com.example.Test.service.JwtFilterRequest;
import com.example.Test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtFilterRequest jwtFilterRequest;
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//		auth.inMemoryAuthentication().withUser("Piyum").password(passwordEncoder().encode("test@123")).authorities("restaurant");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/*
		 * "/FoodiFy/Service/**" for services that use without token
		 * "/FoodiFy/User/**" for functions that use by logged in normal user
		 * "/FoodiFy/Restaurant/**" for functions that use by logged in Restaurant
		 * "/FoodiFy/Premium/**" for functions that use by logged in premium user
		 * "/FoodiFy/Admin/**" for functions that use by logged in admin
		 * 
		 * */
		
		http.cors().and().csrf().disable().authorizeRequests().antMatchers(
         "/Test1/Register/Signupuser",
						"/Test1/auth/login",
						"/Test1/users")

		.permitAll()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/Test1/update/**","/Test1/delete/**").hasAnyAuthority("admin");
		http.authorizeRequests().antMatchers("/Test1/new/**").hasAnyAuthority("user");
		http.authorizeRequests().anyRequest().authenticated();
		
		http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);
		// TODO Auto-generated method stub
//		http.authorizeRequests().anyRequest().authenticated();
//		http.formLogin();
	}
	

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}
	
	

}