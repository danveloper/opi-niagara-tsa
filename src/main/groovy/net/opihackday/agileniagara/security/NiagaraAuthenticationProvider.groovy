package net.opihackday.agileniagara.security

import net.opihackday.agileniagara.service.RemoteUserService
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
  RemoteUserService remoteUserService

  @Override
  Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String email = authentication.name

    if (email.endsWith("objectpartners.com")) {
      Map user = remoteUserService.getUserByEmail(email)
      List grants = user.authorities?.collect { String authority -> new SimpleGrantedAuthority(authority) }
      return new UsernamePasswordAuthenticationToken(email, "", grants)
    } else {
      null
    }
  }

  @Override
  boolean supports(Class<?> authentication) {
    true
  }
}
