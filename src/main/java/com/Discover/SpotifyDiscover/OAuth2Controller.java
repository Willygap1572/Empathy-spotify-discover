package com.Discover.SpotifyDiscover;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class OAuth2Controller extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/", "/filteredTracks", "/top-tracks", "/likelihood", "/exchange", "/home", "/searchDocument", "/showNewTrackForm", "/saveTrack", "/showFormForUpdate/**", "/deleteTrack/**", "/error", "/oauth2/authorization/spotify", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login();
    }
}

