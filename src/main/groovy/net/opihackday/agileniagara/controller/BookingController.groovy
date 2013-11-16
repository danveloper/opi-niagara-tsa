import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BookingController {
    
    @RequestMapping("/bookings/available")
    String available() {
        return ['Booking1', 'Booking2'];
    }

    @RequestMapping("/bookings/create")
    String create() {
        return [name: ""];
    }
    
}