package humanResources.io;
import humanResources.DepartmentsManager;
import humanResources.Employee;
import humanResources.EmployeeGroup;
import humanResources.factory.EmployeeFactory;

import java.util.Collection;

public class ControlledDepartmentManager extends DepartmentsManager {
    protected Source<EmployeeGroup> source;
    private EmployeeFactory factory;

    public ControlledDepartmentManager(String name, EmployeeFactory factory) {
        super(name);
        this.factory = factory;
    }

    public ControlledDepartmentManager(String name, EmployeeGroup[] groups, EmployeeFactory factory) {
        super(name, groups);
        this.factory = factory;
    }

    public Source<EmployeeGroup> getSource() {
        return source;
    }

    public void setSource(Source<EmployeeGroup> source) {
        this.source = source;
    }

    @Override
    public boolean add(EmployeeGroup groupToAdd) {
        EmployeeGroup group = factory.createDepartment(groupToAdd.getName(), (Employee[]) groupToAdd.toArray());
        source.create(group);
        return super.add(group);
    }

    @Override
    public boolean remove(Object o) {
        source.delete((EmployeeGroup) o);
        return super.remove(o);
    }

    @Override
    public boolean addAll(int index, Collection<? extends EmployeeGroup> c) {
        for (EmployeeGroup group: c){
            EmployeeGroup d = factory.createDepartment(group.getName(), (Employee[]) group.toArray());
            source.create(d);
        }
        return super.addAll(index, c);
    }



    @Override
    public boolean addAll(Collection<? extends EmployeeGroup> c) {
        for (EmployeeGroup group: c){
            EmployeeGroup d = factory.createDepartment(group.getName(), (Employee[]) group.toArray());
            source.create(d);
        }
        return super.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (EmployeeGroup group: this){
            if (c.contains(group)){
                source.delete(group);
            }
        }
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (EmployeeGroup group: this){
            if (!c.contains(group)){
                source.delete(group);
            }
        }
        return super.retainAll(c);
    }

    public void store(){
        for (EmployeeGroup group: this){
            if (((ControlledDepartment) group).isChanged){
                source.store(group);
            }
        }
    }

    public void load(){
        for (EmployeeGroup group: this){
            source.load(group);
        }
    }
}
