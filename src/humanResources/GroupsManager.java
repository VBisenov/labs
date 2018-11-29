package humanResources;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface GroupsManager extends List<EmployeeGroup>, Serializable {

    boolean add(EmployeeGroup group);
    int groupsQuantity();
    int remove(EmployeeGroup group);
    boolean remove(String groupName);
    EmployeeGroup getGroup(String groupName);
    EmployeeGroup[] getGroups();
    int employeesQuantity();
    int employeesQuantity(JobTitlesEnum jobTitle);
    Employee employeeBestSalary();
    EmployeeGroup groupOfEmployee(String firstName, String secondName);

    int partTimeEmployeesQuantity();
    int staffEmployeeQuantity();
    int employeesOnTripQuantity();
    Employee[] employeesOnTrip();
    double averageDaysInTrip();
}
