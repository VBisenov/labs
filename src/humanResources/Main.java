package humanResources;

import java.time.LocalDate;
import java.util.Arrays;

public class Main {

    public static void main(String[] args){
        System.out.println(" Department: \n");
        StaffEmployee firstEmployee = new StaffEmployee("Harry", "Potter", JobTitlesEnum.AGENT, 5000);
        StaffEmployee secondEmployee = new StaffEmployee("Newill", "Longbottom", JobTitlesEnum.ENGINEER, 13000);
        StaffEmployee thirdEmployee = new StaffEmployee("Ron", "Wisley", JobTitlesEnum.AGENT, 6000);
        StaffEmployee fourthEmployee = new StaffEmployee("Hermiona", "Granger", JobTitlesEnum.AGENT, 8000);
        StaffEmployee fifthEmployee = new StaffEmployee("Persey", "Wisley", JobTitlesEnum.ADMINISTRATOR, 20000);
        PartTimeEmployee sixthEmployee = new PartTimeEmployee("Draco", "Malfoy", JobTitlesEnum.ANALYST, 4000);

        EmployeeGroup department = new Department("Griffindor",7);

        department.add(firstEmployee);
        //department.add(firstEmployeeEmployee);
        department.add(secondEmployee);
        department.add(thirdEmployee);
        department.add(fourthEmployee);
        department.add(fifthEmployee);
        department.add(sixthEmployee);

        System.out.println(department.employeesQuantity());
        System.out.println(Arrays.asList(department.getEmployees()));
        System.out.println(Arrays.asList(department.getEmployees(JobTitlesEnum.ADMINISTRATOR)));
        System.out.println(Arrays.asList(department.employeesSortedBySalary()));

        System.out.println("\n DepartmentsManager: \n");
        DepartmentsManager departments = new DepartmentsManager("Hogwarts");

        departments.add(department);
        //departments.remove("Hogwarts");
        System.out.println(Arrays.asList(departments.getGroup("Griffindor")));
        System.out.println(Arrays.asList(departments.getGroups()));
        System.out.println("Departments quantity: " + departments.departmentsQuantity());
        System.out.println("Emp quantity: " + departments.employeesQuantity());
        System.out.println("Emp with job title quantity: " + departments.employeesQuantity(JobTitlesEnum.AGENT));
        //System.out.println("Max salary: "+departments.maxSalaryEmployee());
        System.out.println(departments.groupOfEmployee("Harry", "Potter"));
        System.out.println("Groups quantity: " + departments.groupsQuantity());

        BusinessTravel businessTravel = new BusinessTravel("Moscow", LocalDate.of(2018, 11, 1), LocalDate.of(2018, 11, 20), 5000, "Trip to Moscow");
        BusinessTravel businessTravelSecond = new BusinessTravel("London", LocalDate.of(2019, 11, 1), LocalDate.of(2019,11,20), 3000, "Trip to London");
        BusinessTravel businessTravelThird = new BusinessTravel("Paris", LocalDate.of(2018, 10,2), LocalDate.of(2018,10,20), 0, "Trip to paris");
       firstEmployee.add(businessTravel);
    fourthEmployee.add(businessTravelThird);
       secondEmployee.add(businessTravelSecond);

        department.remove(fifthEmployee);
        //      department.remove(JobTitlesEnum.AGENT);
        System.out.println(Arrays.asList(department.getEmployee("Harry", "Potter")));
        System.out.println(Arrays.asList(((Department) department).jobTitlesWithoutRepeat()));
        System.out.println(department.employeeBestSalary());
        System.out.println(Arrays.asList(((Department) department).employeesWithBusinessTrip()));

        System.out.println("\n Project: \n");

        Project project = new Project("Project", department.getEmployees());
        project.remove(fourthEmployee);
        System.out.println(project.getEmployee("Ron", "Wisley"));
        //project.remove("Ron", "Wisley");
        //project.remove(secondEmployee);
        System.out.println(project.employeeBestSalary());
        System.out.println("emp quantity in project: " + project.employeesQuantity());
        System.out.println(Arrays.asList(project.getEmployees()));
        System.out.println(Arrays.asList(project.employeesSortedBySalary()));


        System.out.println("\nProject manager\n");
        GroupsManager groups = new ProjectManager();
        groups.add(department);
        groups.add(project);
        System.out.println("Groups quantity: " + groups.groupsQuantity());
        //System.out.println("Quantity of removing groups: "+groups.remove(department));
        //groups.remove("Project");
        System.out.println(Arrays.asList(groups.getGroups()));
        System.out.println(groups.groupsQuantity());
        System.out.println(groups.getGroup("Griffindor"));
        System.out.println(groups.employeesQuantity());
        System.out.println(groups.employeesQuantity(JobTitlesEnum.ENGINEER));
        System.out.println(groups.employeeBestSalary());
        System.out.println(groups.groupOfEmployee("Newill", "Longbottom"));
        System.out.println(secondEmployee.isOnTrip());
        System.out.println(firstEmployee.isOnTripForPeriod(LocalDate.of(2018, 9, 1), LocalDate.of(2018, 10, 2)));
        System.out.println(Arrays.asList(department.employeesOnTripCurrently()));

        System.out.println(project.partTimeEmployeesQuantity());
        System.out.println(project.staffEmployeesQuantity());
        System.out.println(project.employeesOnTripCurrentlyQuantity());
        System.out.println(Arrays.asList(project.employeesOnTripCurrently()));

        System.out.println(departments.partTimeEmployeesQuantity());
        System.out.println(departments.staffEmployeeQuantity());

        ProjectManager projects = new ProjectManager();
        projects.add(project);


        System.out.println("\n");
        System.out.println(groups.partTimeEmployeesQuantity());
        System.out.println(groups.staffEmployeeQuantity());
        System.out.println(groups.employeesOnTripQuantity());
        System.out.println(Arrays.asList(groups.employeesOnTrip()));
        System.out.println("\n");
        System.out.println(departments.partTimeEmployeesQuantity());
        System.out.println(departments.staffEmployeeQuantity());
        System.out.println(departments.employeesOnTripQuantity());
        System.out.println(Arrays.asList(departments.employeesOnTrip()));

        System.out.println(departments.averageDaysInTrip());
        System.out.println(projects.averageDaysInTrip());
        System.out.println(groups.averageDaysInTrip());
        //создать подкласс для staffEmployee, и при попытке отправить его в командировку вылетает Exception

//        NotMovableStaffEmployee firstNotMovableStaffEmployee = new NotMovableStaffEmployee("Henry","Ford", JobTitlesEnum.CHIEF_ENGINEER, 3000);
//        NotMovableStaffEmployee secondNotMovableStaffEmployee = new NotMovableStaffEmployee("Jack", "London", JobTitlesEnum.DIRECTOR, 5000);
//        department.add(firstNotMovableStaffEmployee);
//        department.add(secondNotMovableStaffEmployee);
//        secondNotMovableStaffEmployee.add(businessTravel);

        //неизменяемый immutable project в методах изменения (add, remove etc) выбрасываются UnsupportedOperationException

//        ImmutableProject immutableProject = new ImmutableProject("Project", department.getEmployees());
//        immutableProject.remove(firstEmployee);
    }
}
