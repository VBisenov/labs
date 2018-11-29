package humanResources.io;

import humanResources.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class GroupsManagerBinaryFileSource extends GroupsManagerFileSource {

    public GroupsManagerBinaryFileSource(String path){
        super(path);
    }

    @Override
    public void load(EmployeeGroup group) {
        File file = new File(getPath()+"\\"+group.getName()+".txt");
        String input;

        try (DataInputStream reader = new DataInputStream(new FileInputStream(file))){
            input = reader.readUTF();
            if (input.equals("Department")) {
                if (!(group instanceof ControlledDepartment)) {
                    throw new InvalidClassException("ControlledDepartment expected");
                }
            } else if (input.equals("Project")) {
                if (!(group instanceof ControlledProject)) {
                    throw new InvalidClassException("ControlledProject expected");
                }
            }

            group.clear();
            group.setName(reader.readUTF());

            int employeesQuantity = reader.readInt();
            Employee employee;
            String firstName, secondName, jobTitle;
            int salary, bonus, btQuantity;

            ArrayList<BusinessTravel> businessTravels = new ArrayList<>();

            for (int i = 0; i < employeesQuantity; i++) {
                firstName = reader.readUTF();
                secondName = reader.readUTF();
                jobTitle = reader.readUTF();
                salary = reader.readInt();
                bonus = reader.readInt();
                btQuantity = reader.readInt();
                if (btQuantity == -1){
                    employee = new PartTimeEmployee(firstName, secondName, JobTitlesEnum.valueOf(jobTitle), salary);
                    group.add(employee);
                } else {
                    for (int j = 0; j < btQuantity; j++) {
                        businessTravels.add(new BusinessTravel(reader.readUTF(), LocalDate.parse(reader.readUTF()), LocalDate.parse(reader.readUTF()), reader.readInt(), reader.readUTF()));
                    }
                    employee = new StaffEmployee(firstName, secondName, JobTitlesEnum.valueOf(jobTitle),salary, businessTravels.toArray(new BusinessTravel[0]));
                    employee.setBonus(bonus);
                    group.add(employee);
                }
            }

        } catch (IOException ex){
            ex.getMessage();
        }
    }

    @Override
    public void store(EmployeeGroup group) {
        File file = new File(getPath()+"\\"+group.getName()+".bin");

        try (DataOutputStream writer = new DataOutputStream(new FileOutputStream(file))){
            if (group instanceof Department){
                writer.writeUTF("Department");
            } else if (group instanceof Project){
                writer.writeUTF("Project");
            }

            writer.writeUTF(group.getName());
            writer.writeInt(group.getSize());

            for (Employee e: group){
                if (e != null) {
                    writer.writeUTF(e.getFirstName());
                    writer.writeUTF(e.getSecondName());
                    writer.writeUTF(e.getJobTitle().toString());
                    writer.writeInt(e.getSalary());
                    writer.writeInt(e.getBonus());
                    if (e instanceof BusinessTraveller){
                        writer.writeInt(e.getTravelsQuantity());
                        for (BusinessTravel bt: ((BusinessTraveller) e).getTravelsArray()){
                            writer.writeUTF(bt.getCityName());
                            writer.writeUTF(bt.getDateStart().toString());
                            writer.writeUTF(bt.getDateEnd().toString());
                            writer.writeInt(bt.getCompensation());
                            writer.writeUTF(bt.getDescription());
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
        File file = new File(getPath()+"\\"+group.getName()+".bin");
        if (file.exists()){
            file.delete();
        }
    }

    @Override
    public void create(EmployeeGroup group) {
        File file = new File(getPath()+"\\"+group.getName()+".bin");
        try {
            file.createNewFile();
        } catch (IOException ex){
            ex.getMessage();
        }
    }
}
