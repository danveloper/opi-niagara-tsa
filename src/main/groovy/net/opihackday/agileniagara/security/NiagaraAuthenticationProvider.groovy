package net.opihackday.agileniagara.security

import net.opihackday.agileniagara.service.RabbitService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
/**
 * User: danielwoods
 * Date: 11/16/13
 */
class NiagaraAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  RabbitService rabbitService

  @Override
  Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String email = authentication.name

    if (email.endsWith("objectpartners.com")) {
      Map<String, String> user = rabbitService.getUserByEmail(email)
      return new UsernamePasswordAuthenticationToken(email, "", [new SimpleGrantedAuthority(user.role)])
    } else {
      null
    }
  }

  @Override
  boolean supports(Class<?> authentication) {
    true
  }
}
