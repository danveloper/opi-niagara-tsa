package net.opihackday.agileniagara.controller

import net.opihackday.agileniagara.service.RabbitService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BookingController {

    @Autowired
    RabbitService rabbitService;

    @RequestMapping("/api/season")
    String listSeasons() {
        return rabbitService.getSeasons();
    }

    @RequestMapping("/api/location")
    String listLocations() {
        return rabbitService.getLocations();
    }

    @RequestMapping("/api/booking/location/{locationId}/season/{seasonId}")
    String listBookings(@PathVariable String locationId, @PathVariable String seasonId) {
        return rabbitService.getBookings(locationId, seasonId);
    }

    @RequestMapping("/api/booking")
    String createBooking(@RequestParam String locationId, @RequestParam String startDate, @RequestParam String endDate) {

        String email = SecurityContextHolder?.context?.authentication?.principal;
        if (email) {
            return rabbitService.createBooking(locationId, startDate, endDate, email);
        }
        throw new RuntimeException("User not authenticated");
    }

}