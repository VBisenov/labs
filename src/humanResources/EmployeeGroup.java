package humanResources;

import java.io.Serializable;
import java.util.List;


public interface EmployeeGroup extends List<Employee>, Serializable {

    String getName();
    int getSize();
    void setName(String name);
    boolean add(Employee employee);
    Employee getEmployee(String firstName, String secondName);
    boolean remove(String firstName, String secondName);
    boolean remove(Employee employee);
    Employee employeeBestSalary();
    int employeesQuantity();
    Employee[] getEmployees();
    Employee[] getEmployees(JobTitlesEnum jobTitle);
    Employee[] employeesSortedBySalary();
    boolean contains(String firstName, String secondName);
    String toString();
    boolean equals(Object obj);
    int hashCode();

    int partTimeEmployeesQuantity();
    int staffEmployeesQuantity();
    int employeesOnTripCurrentlyQuantity();
    Employee[] employeesOnTripCurrently();

    double averageDaysInTrip();
}
