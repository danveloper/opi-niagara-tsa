package net.opihackday.agileniagara

import groovyx.net.http.HTTPBuilder
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.stereotype.Controller
/**
 * User: danielwoods
 * Date: 11/16/13
 */
@EnableAutoConfiguration
@EnableWebSecurity
@Controller
class App extends WebSecurityConfigurerAdapter {

  @Bean
  HTTPBuilder httpBuilder() {
    new HTTPBuilder("https://accounts.google.com")
  }
}
