package humanResources.io;

import humanResources.*;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.time.LocalDate;
import java.util.Scanner;

public class GroupsManagerTextFileSource extends GroupsManagerFileSource {

    public GroupsManagerTextFileSource(String path){
        super(path);
    }

    @Override
    public void load(EmployeeGroup group) {
        File file = new File(getPath()+"\\"+group.getName()+".txt");

        try (Scanner sc = new Scanner(file)){
            String[] input = sc.nextLine().split(";");
            if (input[0].equals("Department")){
                group = new Department(input[1]);
            } else if (input[1].equals("Project")){
                group = new Project(input[1]);
            }

            int employeesQuantity = Integer.parseInt(sc.nextLine());
            Employee employee;

            for (int i = 0; i < employeesQuantity; i++) {
                input = sc.nextLine().split(";");
                if (input[5].equals("partTime")) {
                    employee = new PartTimeEmployee(input[0], input[1], JobTitlesEnum.valueOf(input[2]), Integer.parseInt(input[3]));
                    employee.setBonus(Integer.parseInt(input[4]));
                    group.add(employee);
                } else {
                    employee = new StaffEmployee(input[0], input[1], JobTitlesEnum.valueOf(input[2]), Integer.parseInt(input[3]));
                    employee.setBonus(Integer.parseInt(input[4]));
                    BusinessTravel bt;
                    int btQuantity = Integer.parseInt(input[5]);
                    for (int j = 0; j < btQuantity; j++) {
                        input = sc.nextLine().split(";");
                        bt = new BusinessTravel(input[0], LocalDate.parse(input[1]), LocalDate.parse(input[2]), Integer.parseInt(input[3]), input[4]);
                        ((StaffEmployee) employee).add(bt);
                    }
                    group.add(employee);
                }
            }
        } catch (IOException ex){
            ex.getMessage();
        }
    }

    @Override
    public void store(EmployeeGroup group) {
        File file = new File(getPath()+"\\"+group.getName()+".txt");

        try (PrintWriter writer = new PrintWriter(file)){

            if (group instanceof Department){
                writer.println("Department;"+group.getName());
            } else if (group instanceof Project){
                writer.println("Project;"+group.getName());
            }
            writer.println(group.size());

            for (Employee e: group) {
                if (e != null) {
                    writer.print(e.getFirstName() + ";" + e.getSecondName() + ";" + e.getJobTitle() + ";" + e.getSalary() + ";" + e.getBonus()+ ";");
                    if (!(e instanceof BusinessTraveller)){
                        writer.println("-1");
                    } else {
                        writer.print(e.getTravelsQuantity()+";");
                        writer.println();
                        for (BusinessTravel bt: ((StaffEmployee) e).getTravelsArray()){
                            writer.println(bt.getCityName()+";"+bt.getDateStart()+";"+bt.getDateEnd()+";"+bt.getCompensation()+";"+bt.getDescription()+";");
                        }
                    }
                }
            }
        } catch (IOException ex){
            ex.getMessage();
        }
    }

    @Override
    public void delete(EmployeeGroup group) {
        File file = new File(getPath()+"\\"+group.getName()+".txt");
        if (file.exists()){
            file.delete();
        }
    }

    @Override
    public void create(EmployeeGroup group) {
        File file = new File(getPath() + "\\" + group.getName() + ".txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex){
                ex.getMessage();
            }
        }
    }
}
