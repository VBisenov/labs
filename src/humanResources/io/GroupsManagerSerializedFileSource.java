package humanResources.io;

import humanResources.EmployeeGroup;

import java.io.*;

public class GroupsManagerSerializedFileSource extends GroupsManagerFileSource {
    public GroupsManagerSerializedFileSource(String path){
        super(path);
    }

    @Override
    public void load(EmployeeGroup group) {
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(getPath()+"\\"+group.getName()+".txt"))){
            EmployeeGroup newGroup = (EmployeeGroup) inputStream.readObject();
            group.clear();
            group.addAll(newGroup);
        } catch (IOException | ClassNotFoundException ex){
            ex.getMessage();
        }
    }

    @Override
    public void store(EmployeeGroup group) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(getPath()+"\\"+group.getName()+".ser"))){
            outputStream.writeObject(group);
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
        File file = new File(getPath()+"\\"+group.getName()+".txt");
        if (file.exists()){
            file.delete();
        }

        try {
            file.createNewFile();
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
