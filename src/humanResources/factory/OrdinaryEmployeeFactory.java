package humanResources.factory;

import humanResources.*;

public class OrdinaryEmployeeFactory extends EmployeeFactory {
    @Override
    public EmployeeGroup createDepartment(String name) {
        return new Department(name);
    }

    @Override
    public EmployeeGroup createDepartment(String name, int capacity) {
        return new Department(name, capacity);
    }

    @Override
    public EmployeeGroup createDepartment(String name, Employee[] employees) {
        return new Department(name, employees);
    }

    @Override
    public EmployeeGroup createProject(String name) {
        return new Project(name);
    }

    @Override
    public EmployeeGroup createProject(String name, Employee[] employees) {
        return new Project(name, employees);
    }

    @Override
    public GroupsManager createDepartmentManager(String name) {
        return new DepartmentsManager(name);
    }

    @Override
    public GroupsManager createDepartmentManager(String name, EmployeeGroup[] departments) {
        return new DepartmentsManager(name, departments);
    }

    @Override
    public GroupsManager createProjectManager() {
        return new ProjectManager();
    }

    @Override
    public GroupsManager createProjectManager(EmployeeGroup[] groups) {
        return new ProjectManager(groups);
    }
}
