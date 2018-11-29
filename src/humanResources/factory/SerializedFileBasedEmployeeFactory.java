package humanResources.factory;

import humanResources.Employee;
import humanResources.EmployeeGroup;
import humanResources.GroupsManager;
import humanResources.io.*;

public class SerializedFileBasedEmployeeFactory extends EmployeeFactory {
    @Override
    public EmployeeGroup createDepartment(String name) {
        return new ControlledDepartment(name);
    }

    @Override
    public EmployeeGroup createDepartment(String name, int capacity) {
        return new ControlledDepartment(name, capacity);
    }

    @Override
    public EmployeeGroup createDepartment(String name, Employee[] employees) {
        return new ControlledDepartment(name, employees);
    }

    @Override
    public EmployeeGroup createProject(String name) {
        return new ControlledProject(name);
    }

    @Override
    public EmployeeGroup createProject(String name, Employee[] employees) {
        return new ControlledProject(name, employees);
    }


    @Override
    public GroupsManager createDepartmentManager(String name) {
        ControlledDepartmentManager dep = new ControlledDepartmentManager(name, this);
        dep.setSource(new GroupsManagerSerializedFileSource(".\\test"));
        return dep;
    }

    @Override
    public GroupsManager createDepartmentManager(String name, EmployeeGroup[] departments) {
        ControlledDepartmentManager dep = new ControlledDepartmentManager(name, departments ,this);
        dep.setSource(new GroupsManagerSerializedFileSource(".\\test"));
        return dep;
    }

    @Override
    public GroupsManager createProjectManager() {
        ControlledProjectManager pr = new ControlledProjectManager(this);
        pr.setSource(new GroupsManagerSerializedFileSource(".\\test"));
        return pr;
    }

    @Override
    public GroupsManager createProjectManager(EmployeeGroup[] groups) {
        ControlledProjectManager pr = new ControlledProjectManager(groups, this);
        pr.setSource(new GroupsManagerSerializedFileSource(".\\test"));
        return pr;
    }
}
