package net.opihackday.agileniagara.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

/**
 * User: danielwoods
 * Date: 11/16/13
 */
class NiagaraUserDetailService  implements UserDetailsService{
  @Override
  UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    new User(s, "", [new SimpleGrantedAuthority("ROLE_USER")])
  }
}
