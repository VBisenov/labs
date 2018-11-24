package humanResources;

import java.time.LocalDate;
import java.util.Set;

public interface BusinessTraveller extends Set<BusinessTravel> {

    boolean add(BusinessTravel businessTravel);
    BusinessTravel[] getTravelsArray();

    boolean isOnTrip();
    int isOnTripForPeriod(LocalDate dateStart, LocalDate dateEnd);
}
