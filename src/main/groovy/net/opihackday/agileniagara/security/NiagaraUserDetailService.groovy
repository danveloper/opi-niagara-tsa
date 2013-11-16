package net.opihackday.agileniagara.security

import net.opihackday.agileniagara.service.RabbitService
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
  RabbitService rabbitService

  @Override
  UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Map<String, String> user = rabbitService.getUserByEmail(email)
    new User(user.email, "", [new SimpleGrantedAuthority(user.role)])
  }
}
