package humanResources.io;

import humanResources.Employee;
import humanResources.EmployeeGroup;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GroupsManagerTextFileSource extends GroupsManagerFileSource {

    public GroupsManagerTextFileSource(String path) {
        super.setPath(path);
    }

    private void createFile(File file) {
        try {
            boolean created = file.createNewFile();
            if (created) {
                System.out.println("File " + file.getPath() + " has been created");
            } else {
                System.out.println("File " + file.getPath() + " already exists");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void writeFile(File file, String[] string) {
        try (PrintWriter pw = new PrintWriter(file)) {
            for (int i = 0; i < string.length; i++) {
                pw.println(string[i]);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void load(EmployeeGroup group) {
        File file = new File(getPath() + "\\" + group.getName() + ".txt");
        try (Scanner sc = new Scanner(file)) {
            for (int i = 0; i < file.length(); i++) {
                while (sc.hasNext()) {
                    sc.nextLine();
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void store(EmployeeGroup group) {
        File file = new File(getPath() + "\\" + group.getName() + ".txt");
        String[] result = new String[group.getSize()];

        createFile(file);
        for (int i = 0; i < group.getSize(); i++) {
            if (group.get(i) == null) {
                throw new NoSuchElementException("Fuck");
            }
            result[i] = group.get(i).toString();
        }
        writeFile(file, result);
    }

    @Override
    public void delete(EmployeeGroup group) {
        File file = new File(getPath() + "\\" + group.getName() + ".txt");
        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public void create(EmployeeGroup group) {
        File file = new File(getPath() + "\\" + group.getName() + ".txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.getMessage();
            }
        }
    }
}
