import net.opihackday.agileniagara.service.RemoteUserService

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class UserController {

	RemoteUserService userService;

    @RequestMapping("/user/create")
    String create() {
        return userService.createUser();
    }
}