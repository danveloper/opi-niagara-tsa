package net.opihackday.agileniagara.service

/**
 * Created by jquarfoth on 11/16/13.
 */
public interface BookingService {

    public Map getSeasons();

    public Map getLocations();

    public Map createBooking(Long locationId, LocalDate startDate, LocalDate endDate);

}
