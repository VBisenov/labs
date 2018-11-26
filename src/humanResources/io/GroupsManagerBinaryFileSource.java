package humanResources.io;

import humanResources.EmployeeGroup;

import java.io.File;

public class GroupsManagerBinaryFileSource extends GroupsManagerFileSource {
    @Override
    public void load(EmployeeGroup group) {
        File file = new File("C:\\Users\\Java", group.getName());
    }

    @Override
    public void store(EmployeeGroup group) {
        super.store(group);
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
