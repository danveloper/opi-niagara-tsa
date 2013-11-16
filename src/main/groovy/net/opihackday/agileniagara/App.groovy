package net.opihackday.agileniagara

import groovy.json.JsonSlurper
import groovyx.net.http.HTTPBuilder
import net.opihackday.agileniagara.security.NiagaraAuthenticationProvider
import net.opihackday.agileniagara.security.NiagaraUserDetailService
import net.opihackday.agileniagara.service.RemoteUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ImportResource
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.RememberMeServices
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import sun.plugin.liveconnect.SecurityContextHelper

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
/**
 * User: danielwoods
 * Date: 11/16/13
 */
@EnableAutoConfiguration
@EnableWebSecurity
@Controller
@ImportResource("classpath:META-INF/spring-context.xml")
class App extends WebSecurityConfigurerAdapter {

  @Value('${google.clientId:"aaa"}')
  String clientId

  @Value('${google.clientSecret:"bbbb"}')
  String clientSecret

  @Autowired
  HTTPBuilder googleHttpBuilder

  @Autowired
  RemoteUserService remoteUserService

  @Autowired
  RememberMeServices rememberMeServices

  @RequestMapping("/")
  String hello() {
    "index"
  }

  @RequestMapping("/auth")
  @ResponseBody
  String auth() {
    """\
    <script>
      document.location.href='https://accounts.google.com/o/oauth2/auth?client_id=64727165546.apps.googleusercontent.com&response_type=code&scope=openid%20email&redirect_uri=http://localhost:8080/nofakes&state=security_token%3D138r5719ru3e1%26url%3Dhttp://localhost:8080/nofakes'
    </script>
    """
  }

  @RequestMapping("/nofakes")
  @ResponseBody
  String nofakes(@RequestParam('code') String code, HttpServletRequest request, HttpServletResponse response) {
    Map idResp = googleHttpBuilder.post(path: "/o/oauth2/token",body : [
      code: code,
      "client_id": clientId,
      "client_secret": clientSecret,
      "redirect_uri": 'http://localhost:8080/nofakes',
      "grant_type": 'authorization_code'])

    Map userInfo = new JsonSlurper().parseText("https://www.googleapis.com/oauth2/v1/tokeninfo?id_token=$idResp.id_token".toURL().text)
    def email = userInfo.email

    Map user = remoteUserService.getUserByEmail(email)
    List authorities = user.authorities?.collect { String grant -> new SimpleGrantedAuthority(grant) }

    def auth = new UsernamePasswordAuthenticationToken(email, "", authorities)
    SecurityContextHolder.context.authentication = auth
    rememberMeServices.loginSuccess(request, response, auth)

    response.sendRedirect("/")
  }

  @Bean
  HTTPBuilder restTemplate() {
    new HTTPBuilder("https://accounts.google.com")
  }

  @Bean
  AuthenticationProvider authenticationProvider() {
    new NiagaraAuthenticationProvider()
  }

  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      .antMatchers("/").hasRole("USER")
      .and().authenticationProvider(authenticationProvider())
    http.formLogin().loginPage("/auth").permitAll()
    http.rememberMe().rememberMeServices(rememberMeServices()).key("password")

  }

  @Bean
  public UserDetailsService userDetailsService() {
    new NiagaraUserDetailService()
  }

  @Bean
  public RememberMeServices rememberMeServices() {
    TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices("password", userDetailsService());
    rememberMeServices.setCookieName("cookieName");
    rememberMeServices.setParameter("rememberMe");
    return rememberMeServices;
  }

  public static void main(String[] args) {
    SpringApplication.run this, "run"
  }
}
