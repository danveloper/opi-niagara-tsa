package net.opihackday.agileniagara.security

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException

/**
 * User: danielwoods
 * Date: 11/16/13
 */
class NiagaraAuthenticationProvider implements AuthenticationProvider {
  @Override
  Authentication authenticate(Authentication authentication) throws AuthenticationException {
    return null
  }

  @Override
  boolean supports(Class<?> authentication) {
    return false
  }
}
