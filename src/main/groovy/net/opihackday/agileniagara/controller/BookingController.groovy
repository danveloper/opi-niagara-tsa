import net.opihackday.agileniagara.service.BookingService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Controller
class BookingController {

    BookingService bookingService;

    @RequestMapping("/api/season")
    String listSeasons() {
        return bookingService.seasons();
    }

    @RequestMapping("/api/location")
    String listLocations() {
        return [name: ""];
    }

    @RequestMapping("/api/booking/location/{id}")
    String listLocations(String id) {
        return [name: ""];
    }

    @RequestMapping("/api/booking")
    String listLocations(String id) {
        return [name: ""];
    }

}

//list locations
//list dates