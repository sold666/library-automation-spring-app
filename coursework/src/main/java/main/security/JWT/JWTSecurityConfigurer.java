package main.security.JWT;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private  JWTTokenProvider jwtTokenProvider;

    public JWTSecurityConfigurer(JWTTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        JWTFilter filter = new JWTFilter(jwtTokenProvider);
        builder.exceptionHandling()
                .authenticationEntryPoint(new JWTAuthEntryPoint())
                .and()
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
