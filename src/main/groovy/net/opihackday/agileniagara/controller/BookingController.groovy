package net.opihackday.agileniagara.controller

import net.opihackday.agileniagara.service.RabbitService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import static groovy.json.JsonOutput.toJson

@RestController
class BookingController {

    @Autowired
    RabbitService rabbitService;

    @RequestMapping("/api/season")
    List<Map> listSeasons() {
        String email = SecurityContextHolder?.context?.authentication?.principal;
        return rabbitService.getSeasons(email);
    }

    @RequestMapping("/api/location")
    List<Map> listLocations() {
        return rabbitService.getLocations();
    }

    @RequestMapping("/api/booking/location/{locationId}/season/{seasonId}")
    List<Map> listBookings(@PathVariable String locationId, @PathVariable String seasonId) {
        return rabbitService.getBookings(locationId, seasonId);
    }

    @RequestMapping("/api/booking")
    Map<String, String> createBooking(@RequestParam String locationId, @RequestParam String startDate, @RequestParam String endDate) {

        String email = SecurityContextHolder?.context?.authentication?.principal;
        if (email) {
            return rabbitService.createBooking(email, locationId, startDate, endDate);
        }
        throw new RuntimeException("User not authenticated");
    }

}