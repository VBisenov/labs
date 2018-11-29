package humanResources.factory;

import humanResources.Employee;
import humanResources.EmployeeGroup;
import humanResources.GroupsManager;

public abstract class EmployeeFactory {

    abstract public EmployeeGroup createDepartment(String name);
    abstract public EmployeeGroup createDepartment(String name, int capacity);
    abstract public EmployeeGroup createDepartment(String name, Employee[] employees);
    abstract public EmployeeGroup createProject(String name);
    abstract public EmployeeGroup createProject(String name, Employee[] employees);
    abstract public GroupsManager createDepartmentManager(String name);
    abstract public GroupsManager createDepartmentManager(String name, EmployeeGroup[] departments);
    abstract public GroupsManager createProjectManager();
    abstract public GroupsManager createProjectManager(EmployeeGroup[] groups);

    public static EmployeeFactory getEmployeeFactory(GroupsFactoryEnumerations type) {
        if (type.equals(GroupsFactoryEnumerations.ORDINARY_GROUPS_FACTORY)) {
            return new OrdinaryEmployeeFactory();
        } else if (type.equals(GroupsFactoryEnumerations.BINARY_FILE_BASED_GROUPS_FACTORY)) {
            return new BinaryBasedEmployeeFactory();
        } else if (type.equals(GroupsFactoryEnumerations.TEXT_FILE_BASED_GROUPS_FACTORY)) {
            return new TextFileBasedEmployeeFactory();
        } else if (type.equals(GroupsFactoryEnumerations.SERIALIZED_FILE_BASED_GROUPS_FACTORY)) {
            return new SerializedFileBasedEmployeeFactory();
        }
        return null;
    }
}
