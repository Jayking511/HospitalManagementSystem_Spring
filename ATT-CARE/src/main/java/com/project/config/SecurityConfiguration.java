package com.project.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.project.controller.DoctorController;

import static com.project.model.Permission.ADMIN_CREATE;
import static com.project.model.Permission.ADMIN_DELETE;
import static com.project.model.Permission.ADMIN_READ;
import static com.project.model.Permission.ADMIN_UPDATE;
import static com.project.model.Permission.DOCTOR_UPDATE;
import static com.project.model.Permission.DOCTOR_READ;

import static com.project.model.Permission.RECEPTIONIST_UPDATE;
import static com.project.model.Permission.RECEPTIONIST_READ;
import static com.project.model.Permission.RECEPTIONIST_DELETE;
import static com.project.model.Permission.RECEPTIONIST_CREATE;
import static com.project.model.Role.ADMIN;
import static com.project.model.Role.DOCTOR;
import static com.project.model.Role.RECEPTIONIST;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf()
        .disable()
        .authorizeHttpRequests()
        .requestMatchers(
                "/auth/**",
                "/v2/api-docs",
                "/v3/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui/**",
                "/webjars/**",
                "/swagger-ui.html"
        )
          .permitAll()


			/*
			 * .requestMatchers("/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())
			 * 
			 * 
			 * .requestMatchers(GET, "/management/**").hasAnyAuthority(ADMIN_READ.name(),
			 * MANAGER_READ.name()) .requestMatchers(POST,
			 * "/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
			 * .requestMatchers(PUT, "/management/**").hasAnyAuthority(ADMIN_UPDATE.name(),
			 * MANAGER_UPDATE.name()) .requestMatchers(DELETE,
			 * "/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())
			 */

			
		  	  

			
			  .requestMatchers("/doctors/**").hasAnyRole(ADMIN.name())
			  .requestMatchers(GET, "/doctors/**").hasAnyAuthority(ADMIN_READ.name())
			  .requestMatchers(POST,"/doctors/**").hasAnyAuthority(ADMIN_CREATE.name())
			  .requestMatchers(PUT, "/doctors/**").hasAnyAuthority(ADMIN_UPDATE.name())
			  .requestMatchers(DELETE, "/doctors/**").hasAnyAuthority(ADMIN_DELETE.name())
			 
			  
			  
			  .requestMatchers("/receptionists/**").hasAnyRole(ADMIN.name())
				 
			  .requestMatchers(GET, "/receptionists/**").hasAnyAuthority(ADMIN_READ.name())
			  .requestMatchers(POST,"/receptionists/**").hasAnyAuthority(ADMIN_CREATE.name())
			  .requestMatchers(PUT, "/receptionists/**").hasAnyAuthority(ADMIN_UPDATE.name())
			  .requestMatchers(DELETE, "/receptionists/**").hasAnyAuthority(ADMIN_DELETE.name())
			  
			  
			  .requestMatchers("/patients/**").hasAnyRole(ADMIN.name(),RECEPTIONIST.name())
				 
				
			  .requestMatchers(GET, "/patients/**").hasAnyAuthority(ADMIN_READ.name(),RECEPTIONIST_READ.name())
			  .requestMatchers(POST,"/patients/**").hasAnyAuthority(ADMIN_CREATE.name(), RECEPTIONIST_CREATE.name())
			  .requestMatchers(PUT, "/patients/**").hasAnyAuthority(ADMIN_UPDATE.name(), RECEPTIONIST_UPDATE.name())
			  .requestMatchers(DELETE, "/patients/**").hasAnyAuthority(ADMIN_DELETE.name(),RECEPTIONIST_DELETE.name())
			 
			  .requestMatchers("/appointments/**").hasAnyRole(ADMIN.name(),RECEPTIONIST.name(),DOCTOR.name())
				 
				
			  .requestMatchers(GET, "/appointments/**").hasAnyAuthority(ADMIN_READ.name(),DOCTOR_READ.name(),RECEPTIONIST_READ.name())
			  .requestMatchers(POST,"/appointments/**").hasAnyAuthority(ADMIN_CREATE.name(),RECEPTIONIST_CREATE.name())
			  .requestMatchers(PUT, "/appointments/**").hasAnyAuthority(ADMIN_UPDATE.name(), RECEPTIONIST_UPDATE.name(),DOCTOR_UPDATE.name())
			  .requestMatchers(DELETE, "/appointments/**").hasAnyAuthority(ADMIN_DELETE.name(),RECEPTIONIST_DELETE.name())
			 
			 
       /* .requestMatchers("/admin/**").hasRole(ADMIN.name())

        .requestMatchers(GET, "/admin/**").hasAuthority(ADMIN_READ.name())
        .requestMatchers(POST, "/admin/**").hasAuthority(ADMIN_CREATE.name())
        .requestMatchers(PUT, "/admin/**").hasAuthority(ADMIN_UPDATE.name())
        .requestMatchers(DELETE, "/admin/**").hasAuthority(ADMIN_DELETE.name())*/


        .anyRequest()
          .authenticated()
        .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .logout()
        .logoutUrl("/auth/logout")
        .addLogoutHandler(logoutHandler)
        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
    ;

    return http.build();
  }
}
