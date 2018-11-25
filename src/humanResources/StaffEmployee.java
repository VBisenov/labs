package humanResources;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import static java.time.temporal.ChronoUnit.DAYS;

public class StaffEmployee extends Employee implements BusinessTraveller{
    private CycleLinkedList<BusinessTravel> businessTravels = new CycleLinkedList<>();
    private int bonus;
    private int travelsQuantity;

    public StaffEmployee(String firstName, String secondName){
        super(firstName, secondName);
    }

    public StaffEmployee(String firstName, String secondName, JobTitlesEnum jobTitle, int salary){
        super(firstName, secondName, jobTitle, salary);
    }

    public StaffEmployee(String firstName, String secondName, JobTitlesEnum jobTitle, int salary, BusinessTravel[] businessTravels){
        super(firstName, secondName,jobTitle, salary);
        this.businessTravels.addAll(businessTravels);
    }

    @Override
    public int compareTo(Employee o) {
        return super.compareTo(o);
    }

    @Override
    int getBonus() {
        return bonus;
    }

    @Override
    void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getTravelsQuantity() {
        return travelsQuantity;
    }

    private boolean isTravelsDatesOverlap(BusinessTravel businessTravel){
        for (int i = 0; i < businessTravels.size(); i++) {
            if (businessTravel.getDateStart().isAfter(businessTravels.getEl(i).getDateStart()) && businessTravel.getDateStart().isBefore(businessTravels.getEl(i).getDateEnd())){
                return true;
            } else if (businessTravel.getDateEnd().isAfter(businessTravels.getEl(i).getDateStart()) && businessTravel.getDateEnd().isBefore(businessTravels.getEl(i).getDateEnd())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return businessTravels.size();
    }

    @Override
    public boolean isEmpty() {
        return businessTravels.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return businessTravels.contains(o);
    }

    @Override
    public Iterator<BusinessTravel> iterator() {
        return businessTravels.iterator();
    }

    @Override
    public Object[] toArray() {
        return businessTravels.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return businessTravels.toArray(a);
    }

    @Override
    public boolean add(BusinessTravel businessTravel) {
        if (businessTravel == null) {
            return false;
        }
        businessTravels.add(businessTravel);
        travelsQuantity++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return businessTravels.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return businessTravels.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends BusinessTravel> c) {
        return businessTravels.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return businessTravels.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return businessTravels.retainAll(c);
    }

    @Override
    public void clear() {
        businessTravels.clear();
    }

    @Override
    public BusinessTravel[] getTravelsArray() {
        BusinessTravel[] travelsArray = new BusinessTravel[travelsQuantity];
        for (int i = 0; i < travelsQuantity; i++) {
            travelsArray[i] = businessTravels.getEl(i);
        }
        return travelsArray;
    }

    @Override
    public boolean isOnTrip() {
        for (int i = 0; i < businessTravels.size(); i++) {
            if (businessTravels.getEl(i).getDateStart().isBefore(LocalDate.now())
                    && businessTravels.getEl(i).getDateEnd().isAfter(LocalDate.now())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int isOnTripForPeriod(LocalDate dateStart, LocalDate dateEnd) {
        if (dateEnd.isBefore(dateStart))
            throw new IllegalArgumentException("Entering date Start must be before date End");
        int count = 0;

        for (int i = 0; i < businessTravels.size(); i++) {
            if (dateStart.isAfter(businessTravels.getEl(i).getDateStart()) && dateEnd.isBefore(businessTravels.getEl(i).getDateEnd())) {
                count = (int) DAYS.between(dateStart, dateEnd);
            } else if (dateStart.isBefore(businessTravels.getEl(i).getDateStart()) && dateEnd.isAfter(businessTravels.getEl(i).getDateEnd())) {
                count = (int) DAYS.between(businessTravels.getEl(i).getDateStart(), businessTravels.getEl(i).getDateEnd());
            } else if (dateStart.isAfter(businessTravels.getEl(i).getDateStart()) && dateEnd.isAfter(businessTravels.getEl(i).getDateEnd())) {
                count = (int) DAYS.between(dateStart, businessTravels.getEl(i).getDateEnd());
            } else if (dateStart.isBefore(businessTravels.getEl(i).getDateStart()) && dateEnd.isBefore(businessTravels.getEl(i).getDateEnd())){
                count = (int) DAYS.between(businessTravels.getEl(i).getDateStart(), dateEnd);
            }
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (bonus != 0) sb.append(" bonus: ").append(bonus);
        if (travelsQuantity > 0) sb.append("| Business travels: ").append(Arrays.asList(getTravelsArray()));
        return sb.toString();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof StaffEmployee)) return false;

        StaffEmployee se = (StaffEmployee) obj;

        return this.getFirstName().equals(se.getFirstName()) &&
                this.getSecondName().equals(se.getSecondName()) &&
                this.getJobTitle().equals(se.getJobTitle()) &&
                this.getSalary() == se.getSalary() &&
                this.getBonus() == se.getBonus() &&
                this.getTravelsArray().length == se.getTravelsArray().length;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < getTravelsArray().length; i++) {
            hash = hash ^ getTravelsArray()[i].hashCode();
        }

        return hash ^ this.getJobTitle().hashCode() ^ this.getFirstName().hashCode() ^
                this.getSecondName().hashCode() ^ this.getBonus() ^ this.getSalary();
    }
}
