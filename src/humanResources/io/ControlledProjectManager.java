package humanResources.io;

import humanResources.Employee;
import humanResources.EmployeeGroup;
import humanResources.ProjectManager;

import java.util.Collection;

public class ControlledProjectManager extends ProjectManager {
    protected Source<EmployeeGroup> source;

    public ControlledProjectManager(){
    }

    public ControlledProjectManager(EmployeeGroup[] employeeGroup){
        super(employeeGroup);
    }

    @Override
    public boolean add(EmployeeGroup group){
        /*
        на основе группы сотрудников создается экземпляр ControlledProjectManager
         */
        source.create(group);
        return super.add(group);
    }

    @Override
    public int remove(EmployeeGroup group) {
        source.delete(group);
        return super.remove(group);
    }

    @Override
    public boolean addAll(int index, Collection<? extends EmployeeGroup> c) {
        for (EmployeeGroup group: c){
            add(group);
        }
        return super.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (EmployeeGroup group: getGroups()){
            if (c.contains(group)){
                remove(group);
            }
        }
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (EmployeeGroup group: getGroups()){
            if (!c.contains(group)){
                remove(group);
            }
        }
        return super.retainAll(c);
    }

    void store(){
        for (EmployeeGroup group: this){
            if (((ControlledProject) group).isChanged){
                source.store(group);
                ((ControlledProject) group).isChanged = false;
            }
        }
    }

    void load(){
        for (EmployeeGroup group: this){
            source.load(group);
        }
    }
}
