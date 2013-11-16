package net.opihackday.agileniagara

import org.springframework.context.annotation.AnnotationConfigApplicationContext

/**
 * User: danielwoods
 * Date: 11/16/13
 */
class TestRabbit {

  static void main(_) {
    def ctx = new AnnotationConfigApplicationContext(App)
    def rabbit = ctx.getBean('rabbitTemplate')
    println rabbit.convertSendAndReceive("booking", "booking.request", [username: "daniel.woods@objectpartners.com"])
  }
}
