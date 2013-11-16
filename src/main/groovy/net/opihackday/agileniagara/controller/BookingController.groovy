import net.opihackday.agileniagara.service.RabbitService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class BookingController {

    @Autowired
    RabbitService bookingService;

    @RequestMapping("/api/season")
    String listSeasons() {
        return bookingService.getSeasons();
    }

    @RequestMapping("/api/location")
    String listLocations() {
        return bookingService.getLocations();
    }

    @RequestMapping("/api/booking/location/{locationId}/season/{seasonId}")
    String listBookings(@PathVariable String locationId, @PathVariable String seasonId) {
        return bookingService.getBookings(locationId, seasonId);
    }

    @RequestMapping("/api/booking")
    String createBooking(String locationId, String startDate, String endDate) {

        String email = SecurityContextHolder?.context?.authentication?.principal;
        if (email) {
            return bookingService.createBooking(locationId, startDate, endDate, email);
        }
        throw new RuntimeException("User not authenticated");
    }

}

//list locations
//list dates