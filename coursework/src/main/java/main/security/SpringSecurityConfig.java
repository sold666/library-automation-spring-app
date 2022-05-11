package main.security;

import main.security.JWT.JWTSecurityConfigurer;
import main.security.JWT.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/sign_in").permitAll()
                .antMatchers(HttpMethod.GET, "/jc/journals").hasRole("LIBRARIAN")
                .antMatchers(HttpMethod.POST, "/jc/add journal").hasRole("LIBRARIAN")
                .antMatchers(HttpMethod.GET, "/jc/{id}").hasRole("LIBRARIAN")
                .antMatchers(HttpMethod.GET, "/jc/book/{bookID}").hasRole("LIBRARIAN")
                .antMatchers(HttpMethod.GET, "/jc/client/{clientID}").hasRole("LIBRARIAN")
                .antMatchers(HttpMethod.PATCH, "/jc/update/{id}/date").hasRole("LIBRARIAN")
                .antMatchers(HttpMethod.DELETE, "/jc/{id}").hasRole("LIBRARIAN")
                .antMatchers(HttpMethod.DELETE, "/jc/client/{clientID}").hasRole("LIBRARIAN")

                .antMatchers(HttpMethod.GET, "/cc/clients").hasRole("LIBRARIAN")
                .antMatchers(HttpMethod.POST, "/cc/add client").hasRole("LIBRARIAN")
                .antMatchers(HttpMethod.GET, "/cc/{id}").hasRole("LIBRARIAN")
                .antMatchers(HttpMethod.GET, "/cc/full name").hasRole("LIBRARIAN")
                .antMatchers(HttpMethod.GET, "/cc/full name/fine").permitAll()
                .antMatchers(HttpMethod.GET, "/cc/full name/days").permitAll()
                .antMatchers(HttpMethod.GET, "/cc/full name/num").permitAll()
                .antMatchers(HttpMethod.DELETE, "/cc/{id}").hasRole("LIBRARIAN")

                .antMatchers(HttpMethod.GET, "/btc/books type").permitAll()
                .antMatchers(HttpMethod.POST, "/btc/add_type").hasRole("LIBRARIAN")
                .antMatchers(HttpMethod.GET, "/btc/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/btc/name/{name}").permitAll()
                .antMatchers(HttpMethod.PATCH, "/btc/update/{id}/days").hasRole("LIBRARIAN")
                .antMatchers(HttpMethod.PATCH, "/btc/update/{id}/cnt").hasRole("LIBRARIAN")
                .antMatchers(HttpMethod.DELETE, "/btc/{name}").hasRole("LIBRARIAN")

                .antMatchers(HttpMethod.GET, "/bc/books").permitAll()
                .antMatchers(HttpMethod.POST, "/bc/add_book").hasRole("LIBRARIAN")
                .antMatchers(HttpMethod.GET, "/bc/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/bc/name/{name}").permitAll()
                .antMatchers(HttpMethod.GET, "/bc/search_client_book").permitAll()
                .antMatchers(HttpMethod.PATCH, "/bc/update/{id}/cnt").hasRole("LIBRARIAN")
                .antMatchers(HttpMethod.DELETE, "/bc/{name}").hasRole("LIBRARIAN")
                .anyRequest().authenticated()
                .and()
                .apply(new JWTSecurityConfigurer(jwtTokenProvider));

    }
}
