package com.example.Best_Prac.Config;

import com.example.Best_Prac.JWT.JwtFilter;
import com.example.Best_Prac.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

	private final LoginService userDetails; // Ensure this is a bean
	private final JwtFilter filter;

	public SecurityConfig(LoginService userDetails, JwtFilter filter) {
		this.userDetails = userDetails;
		this.filter = filter;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		return http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/h2-console/**","/login").permitAll()
						.requestMatchers("/bidding/add").hasAuthority("BIDDER")
						.requestMatchers("/bidding/update/").hasAuthority("APPROVER")
						.requestMatchers("/bidding/list", "/bidding/delete/").hasAnyAuthority("BIDDER", "APPROVER")
						.anyRequest().authenticated()
				)
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				).authenticationProvider(authenticationProvider())
				.exceptionHandling(ex -> ex
						.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
				)
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.build();

	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetails);
		provider.setPasswordEncoder(getPasswordEncoder());
		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}

//-------------------------------------------------------------------------------------------------------------------------

//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private LoginService loginService;
//
//    @Autowired
//    private AuthenticationFilter authFilter;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(loginService);
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/h2-console/**", "/login");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//            .authorizeRequests()
//            .antMatchers("/bidding/add").hasAuthority("BIDDER")
//            .antMatchers("/bidding/update/**").hasAuthority("APPROVER")
//            .antMatchers("/bidding/list", "/bidding/delete/**").hasAnyAuthority("BIDDER", "APPROVER")
//            .anyRequest().authenticated()
//            .and()
//            .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//            .and()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        // For simplicity, NoOpPasswordEncoder is used. Replace with BCryptPasswordEncoder in production.
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Override
//    @Bean
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
//}