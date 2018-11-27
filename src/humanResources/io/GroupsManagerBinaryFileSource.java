package humanResources.io;

import humanResources.Employee;
import humanResources.EmployeeGroup;
import humanResources.LinkedList;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GroupsManagerBinaryFileSource extends GroupsManagerFileSource {

    public GroupsManagerBinaryFileSource(String path) {
        super.setPath(path);
    }


    //todo here
    @Override
    public void load(EmployeeGroup group) {
    }


    @Override
    public void store(EmployeeGroup group) {
        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(getPath()+"\\"+group.getName()+".txt"))){
            for (int i = 0; i < group.getSize(); i++) {
                outputStream.writeUTF(group.get(i).toString() + "\n");
            }
        } catch (IOException ex){
            ex.getMessage();
        }
    }

    @Override
    public void delete(EmployeeGroup group) {
        super.delete(group);
    }

    @Override
    public void create(EmployeeGroup group) {
        super.create(group);
    }
}
