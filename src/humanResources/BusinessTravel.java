package humanResources;

import java.io.Serializable;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class BusinessTravel implements Serializable {
    private final String cityName;
    private final LocalDate dateStart, dateEnd;
    private final int compensation;
    private final String description;

    public BusinessTravel() {
        this("", LocalDate.now(), LocalDate.now().plusDays(1), 0, "");
    }

    public BusinessTravel(String cityName, LocalDate dateStart, LocalDate dateEnd, int compensation, String description) {
        if (dateStart.isAfter(dateEnd)) throw new IllegalArgumentException("Date start must be before date end");
        if (compensation < 0) throw new IllegalArgumentException("Field compensation must be positive");
        this.cityName = cityName;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.compensation = compensation;
        this.description = description;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public String getCityName() {
        return cityName;
    }

    public int getDaysCount() {
        return (int)DAYS.between(dateStart, dateEnd);
    }

    public int getCompensation() {
        return compensation;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return String.format("%s %d (%d p.). %s", getCityName(), getDaysCount(), getCompensation(), getDescription());
    }

    @Override
    public boolean equals(Object obj) {
        BusinessTravel businessTravel = (BusinessTravel) obj;
        return cityName.equals(businessTravel.getCityName()) && businessTravel.getDateStart().equals(dateStart) && businessTravel.getDateEnd().equals(dateEnd)
                && compensation == businessTravel.getCompensation() && description.equals(businessTravel.getDescription());
    }

    @Override
    public int hashCode() {
        return getCityName().hashCode() ^ getDateStart().hashCode() ^ getDateEnd().hashCode() ^ getCompensation() ^ getDescription().hashCode();
    }
}
