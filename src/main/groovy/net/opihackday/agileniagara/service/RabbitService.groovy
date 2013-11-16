package net.opihackday.agileniagara.service

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * User: danielwoods
 * Date: 11/16/13
 */
@Service
class RabbitService {

  @Autowired
  RabbitTemplate rabbitTemplate

  Map getUserByEmail(String email) {
    (Map)rabbitTemplate.convertSendAndReceive("users", "lookup", email)
  }
}
