package net.opihackday.agileniagara.controller

import net.opihackday.agileniagara.service.RabbitService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class UserController {

  @Autowired
  RabbitService rabbitService;

  @RequestMapping("/whoami")
  @ResponseBody
  String create() {
    return SecurityContextHolder.context.authentication.principal
  }
}