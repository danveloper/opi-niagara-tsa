package net.opihackday.agileniagara.security

import net.opihackday.agileniagara.service.RemoteUserService
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
  @Autowired
  RemoteUserService remoteUserService

  @Override
  UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    Map user = remoteUserService.getUserByEmail(s)
    List authorities = user.authorities?.collect { String grant -> new SimpleGrantedAuthority(grant)}
    new User((String)user.email, "", authorities)
  }
}
