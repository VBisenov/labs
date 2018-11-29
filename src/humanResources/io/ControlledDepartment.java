package humanResources.io;

import humanResources.Department;
import humanResources.Employee;
import humanResources.JobTitlesEnum;

import java.util.Collection;

public class ControlledDepartment extends Department {
    protected boolean isChanged;

    public ControlledDepartment(String name) {
        super(name);
    }

    public ControlledDepartment(String name, int capacity) {
        super(name, capacity);
    }

    public ControlledDepartment(String name, Employee[] employees) {
        super(name, employees);
    }

    public boolean isChanged() {
        return isChanged;
    }

    @Override
    public void setName(String name) {
        isChanged = true;
        super.setName(name);
    }

    @Override
    public Employee set(int index, Employee element) {
        isChanged = true;
        return super.set(index, element);
    }

    @Override
    public boolean add(Employee employeeToAdd) {
        isChanged = true;
        return super.add(employeeToAdd);
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
    public boolean addAll(Collection<? extends Employee> c) {
        isChanged = true;
        return super.addAll(c);
    }

    @Override
    public boolean remove(Employee employee) {
        isChanged = true;
        return super.remove(employee);
    }

    @Override
    public boolean remove(String firstName, String secondName) {
        isChanged = true;
        return super.remove(firstName, secondName);
    }

    @Override
    public boolean remove(Object o) {
        isChanged = true;
        return super.remove(o);
    }

    @Override
    public Employee remove(int index) {
        isChanged = true;
        return super.remove(index);
    }

    @Override
    public boolean remove(JobTitlesEnum jobTitle) {
        isChanged = true;
        return super.remove(jobTitle);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        isChanged = true;
        return super.removeAll(c);
    }
}
