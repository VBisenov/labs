package humanResources;


import humanResources.factory.EmployeeFactory;
import humanResources.factory.TextFileBasedEmployeeFactory;
import humanResources.io.ControlledDepartmentManager;
import humanResources.io.GroupsManagerBinaryFileSource;
import humanResources.io.GroupsManagerSerializedFileSource;
import humanResources.io.GroupsManagerTextFileSource;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println(" Department: \n");
        StaffEmployee firstEmployee = new StaffEmployee("Harry", "Potter", JobTitlesEnum.AGENT, 5000);
        StaffEmployee secondEmployee = new StaffEmployee("Newill", "Longbottom", JobTitlesEnum.ENGINEER, 13000);
        Employee thirdEmployee = new PartTimeEmployee("Ron", "Wisley", JobTitlesEnum.AGENT, 6000);
        Employee fourthEmployee = new PartTimeEmployee("Hermiona", "Granger", JobTitlesEnum.AGENT, 8000);
        Employee fifthEmployee = new PartTimeEmployee("Persey", "Wisley", JobTitlesEnum.ADMINISTRATOR, 20000);
        Employee sixthEmployee = new PartTimeEmployee("Draco", "Malfoy", JobTitlesEnum.ANALYST, 4000);

        EmployeeGroup department = new Department("Griffindor");
        department.add(firstEmployee);
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
        DepartmentsManager departments = new DepartmentsManager("depManager");
        departments.add(department);
        System.out.println(departments.getGroup("Griffindor"));
        System.out.println(Arrays.asList(departments.getGroups()));
        System.out.println("Departments quantity: " + departments.departmentsQuantity());
        System.out.println("Emp quantity: " + departments.employeesQuantity());
        System.out.println("Emp with job title quantity: " + departments.employeesQuantity(JobTitlesEnum.AGENT));
        //System.out.println("Max salary: "+departments.maxSalaryEmployee());
        System.out.println(departments.groupOfEmployee("Harry", "Potter"));
        System.out.println("Groups quantity: " + departments.groupsQuantity());

        BusinessTravel businessTravel = new BusinessTravel("Moscow", LocalDate.of(2018, 11, 1), LocalDate.of(2018, 11, 20), 5000, "Trip to Moscow");
        BusinessTravel businessTravelSecond = new BusinessTravel("London", LocalDate.of(2019, 11, 1), LocalDate.of(2019, 11, 20), 3000, "Trip to London");
        firstEmployee.add(businessTravel);
        secondEmployee.add(businessTravelSecond);

        //      department.remove(JobTitlesEnum.AGENT);
        System.out.println(Arrays.asList(department.getEmployee("Harry", "Potter")));
        System.out.println(Arrays.asList(((Department) department).jobTitlesWithoutRepeat()));
        System.out.println(department.employeeBestSalary());
        System.out.println(Arrays.asList(((Department) department).employeesWithBusinessTrip()));

        System.out.println("\n Project: \n");
        Project project = new Project("Lesson", department.getEmployees());
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
        System.out.println(firstEmployee.isOnTrip());
        System.out.println(firstEmployee.isOnTripForPeriod(LocalDate.of(2018, 9, 1), LocalDate.of(2018, 12, 2)));
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


        firstEmployee.add(businessTravelSecond);
        System.out.println(firstEmployee.toString());

        firstEmployee.setBonus(1000);

//        GroupsManagerTextFileSource textFileSource = new GroupsManagerTextFileSource("C:\\Java\\Output");
//        textFileSource.store(department);
//        textFileSource.load(department);
//        System.out.println();
//
//        GroupsManagerBinaryFileSource binaryFileSource = new GroupsManagerBinaryFileSource("C:\\Java\\Output");
//        binaryFileSource.store(department);
//        binaryFileSource.load(department);
//
//        GroupsManagerSerializedFileSource serializedFileSource = new GroupsManagerSerializedFileSource("C:\\Java\\Output");
//        serializedFileSource.store(department);
//        serializedFileSource.load(department);

        Department forRemove = new Department("qwerty");
        forRemove.add(firstEmployee);
//        department.removeAll(forRemove);

        department.remove(5);
        department.remove(firstEmployee);
        project.removeAll(department);
    }
}
