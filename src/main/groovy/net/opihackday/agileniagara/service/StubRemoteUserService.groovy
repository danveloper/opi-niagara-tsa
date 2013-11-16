package net.opihackday.agileniagara.service

import org.springframework.stereotype.Service

/**
 * User: danielwoods
 * Date: 11/16/13
 */
@Service
class StubRemoteUserService {

  Map getUserByEmail(String email) {
    [email: email, authorities: ['ROLE_USER']]
  }
}
