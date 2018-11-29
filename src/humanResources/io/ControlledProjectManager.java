package humanResources.io;

import humanResources.Employee;
import humanResources.EmployeeGroup;
import humanResources.Project;
import humanResources.ProjectManager;
import humanResources.factory.EmployeeFactory;

import java.util.Collection;

public class ControlledProjectManager extends ProjectManager {
    protected Source<EmployeeGroup> source;
    private EmployeeFactory factory;

    public ControlledProjectManager(EmployeeFactory factory) {
        this.factory = factory;
    }

    public ControlledProjectManager(EmployeeGroup[] employeeGroup, EmployeeFactory factory){
        super(employeeGroup);
        this.factory = factory;
    }

    public Source<EmployeeGroup> getSource() {
        return source;
    }

    public void setSource(Source<EmployeeGroup> source) {
        this.source = source;
    }



    @Override
    public boolean add(EmployeeGroup group) {
        EmployeeGroup employeeGroup = factory.createProject(group.getName(), (Employee[]) group.toArray());
        source.create(employeeGroup);
        return super.add(employeeGroup);
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof EmployeeGroup)){
            return false;
        }
        source.delete((EmployeeGroup) o);
        return super.remove(o);
    }

    @Override
    public void add(int index, EmployeeGroup element) {
        EmployeeGroup gr = factory.createProject(element.getName(), (Employee[]) element.toArray());
        source.create(gr);
        super.add(index, element);
    }

    @Override
    public boolean addAll(int index, Collection<? extends EmployeeGroup> c) {
        for (EmployeeGroup group: c){
            EmployeeGroup gr = factory.createProject(group.getName(), (Employee[]) group.toArray());
            source.create(gr);
        }
        return super.addAll(index, c);
    }

    @Override
    public boolean addAll(Collection<? extends EmployeeGroup> c) {
        for (EmployeeGroup group: c){
            EmployeeGroup gr = factory.createProject(group.getName(), (Employee[]) group.toArray());
            source.create(gr);
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
            if (((ControlledProject) group).isChanged){
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
