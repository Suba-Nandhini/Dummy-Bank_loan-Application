package com.suba.Bankloanapplication.config;

import com.suba.Bankloanapplication.model.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityFilter {

    private final AuthenticationProvider authenticationProvider;

    private final  JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityFilter(AuthenticationProvider authenticationProvider, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;

    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf(csrfConfig->csrfConfig.disable())
                .sessionManagement(sessionMangConfig->sessionMangConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider)
                .authorizeHttpRequests(authConfig->{
                    // Whitelist endpoints
                    authConfig.requestMatchers(AUTH_WHITELIST).permitAll();

                    //  LOGIN OR REGISTER USER
                    authConfig.requestMatchers(HttpMethod.POST,"auth/registerAdmin").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST,"auth/authenticate","auth/register").permitAll();


                    // Admin role granting endpoint
                    authConfig.requestMatchers(HttpMethod.POST, "/auth/assign-role").hasAuthority(Permissions.ASSIGN_ROLE.name());
                    //Error endpoints
                    authConfig.requestMatchers("/error").permitAll();

                    //account end points
                    authConfig.requestMatchers(HttpMethod.POST,"/account/request-deletion").hasAuthority(Permissions.REQUEST_DELETION.name());
                    authConfig.requestMatchers(HttpMethod.POST,"/account/approve-deletion/{requestId}").hasAuthority(Permissions.APPROVE_DELETION.name());
                    authConfig.requestMatchers(HttpMethod.GET,"/account/pending-deletion-request").hasAuthority(Permissions.VIEW_PENDING_DELETIONS.name());


                    //Loan end points
                    authConfig.requestMatchers(HttpMethod.GET,"loan/admin/approvalView","loan/admin/approvalViewXMLFormat").hasAuthority(Permissions.GET_LIST.name());
                    authConfig.requestMatchers(HttpMethod.PUT,"loan/admin/approve/{id}").hasAuthority(Permissions.APPROVE_LOAN.name());
                    authConfig.requestMatchers(HttpMethod.GET,"loan/admin/view").hasAuthority(Permissions.GET_LIST.name());
                    authConfig.requestMatchers(HttpMethod.GET,"loan/admin/transaction").hasAuthority(Permissions.GET_LIST.name());
                    authConfig.requestMatchers(HttpMethod.POST,"loan/add").hasAuthority(Permissions.GET_LOAN.name());

                    //customer endpoints

                    authConfig.requestMatchers(HttpMethod.POST, "addCustomer").hasAuthority((Permissions.CREATE_ACCOUNT.name()));
                    authConfig.requestMatchers(HttpMethod.GET,"/customer/{accountNumber}/details").hasAuthority(Permissions.GET_LOAN.name());
                    authConfig.requestMatchers(HttpMethod.POST,"/credit").hasAuthority(Permissions.GET_LOAN.name());
                    authConfig.requestMatchers(HttpMethod.PUT,"/updateCustomer").hasAuthority(Permissions.GET_LOAN.name());

                    // Any other request requires authentication
                    authConfig.anyRequest().authenticated();

                });
        return http.build();
    }
    private static final String[] AUTH_WHITELIST = {
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/mail/send",
    };


}
