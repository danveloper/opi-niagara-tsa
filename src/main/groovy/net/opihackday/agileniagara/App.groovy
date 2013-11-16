package net.opihackday.agileniagara

import groovy.json.JsonSlurper
import groovyx.net.http.HTTPBuilder
import net.opihackday.agileniagara.security.NiagaraAuthenticationProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ImportResource
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

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

  @RequestMapping("/hi")
  @ResponseBody
  String hello() {
    "Hello"
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
  String nofakes(@RequestParam('code') String code, HttpServletResponse response) {
    println code
    Map idResp = googleHttpBuilder.post(path: "/o/oauth2/token",body : [
      code: code,
      "client_id": clientId,
      "client_secret": clientSecret,
      "redirect_uri": 'http://localhost:8080/nofakes',
      "grant_type": 'authorization_code'])

    Map userInfo = new JsonSlurper().parseText("https://www.googleapis.com/oauth2/v1/tokeninfo?id_token=$idResp.id_token".toURL().text)
    def email = userInfo.email
  }

  @RequestMapping("/login")
  @ResponseBody
  String login(@RequestParam('foo') String foo) {
    "LOL HI"
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
      .antMatchers("/hi").hasRole("USER")
      .and().authenticationProvider(authenticationProvider())

  }

  public static void main(String[] args) {
    SpringApplication.run this, "run"
  }
}
