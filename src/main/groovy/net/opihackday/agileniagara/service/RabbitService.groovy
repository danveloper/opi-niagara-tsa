package net.opihackday.agileniagara.service

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired

/**
 * User: danielwoods
 * Date: 11/16/13
 */
class RabbitService {

  @Autowired
  RabbitTemplate rabbitTemplate

  Map getUserByEmail(String email) {
    (Map)rabbitTemplate.convertSendAndReceive("users", "lookup", email)
  }
}
