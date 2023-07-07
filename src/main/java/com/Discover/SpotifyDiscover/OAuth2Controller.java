package com.Discover.SpotifyDiscover;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class OAuth2Controller extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/", "/top-tracks","/exchange", "/home", "/searchDocument", "/showNewTrackForm", "/saveTrack", "/showFormForUpdate/**", "/deleteTrack/**", "/error", "/oauth2/authorization/spotify", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login();
    }
}

