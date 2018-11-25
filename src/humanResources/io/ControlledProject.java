package humanResources.io;

import humanResources.Employee;
import humanResources.Project;

import java.util.Collection;

public class ControlledProject extends Project {
    protected boolean isChanged;

    public ControlledProject(String name) {
        super(name);
    }

    public ControlledProject(String name, Project project) {
        super(name, project.getEmployees());
    }

    @Override
    public boolean add(Employee employee) {
        isChanged = true;
        return super.add(employee);
    }

    @Override
    public boolean addAll(Collection<? extends Employee> c) {
        isChanged = true;
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Employee> c) {
        isChanged = true;
        return super.addAll(index, c);
    }

    @Override
    public void add(int index, Employee element) {
        isChanged = true;
        super.add(index, element);
    }

    @Override
    public Employee remove(int index) {
        isChanged = true;
        return super.remove(index);
    }

    @Override
    public boolean remove(Employee employee) {
        isChanged = true;
        return super.remove(employee);
    }

    @Override
    public boolean remove(Object o) {
        isChanged = true;
        return super.remove(o);
    }

    @Override
    public boolean remove(String firstName, String secondName) {
        isChanged = true;
        return super.remove(firstName, secondName);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        isChanged = true;
        return super.removeAll(c);
    }
}
