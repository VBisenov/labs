package humanResources;

import java.util.Collection;

public class ImmutableProject extends Project {

    public ImmutableProject(String name) {
        super(name);
    }

    public ImmutableProject(String name, Employee[] employees) {
        super(name, employees);
    }

    @Override
    public boolean add(Employee employee) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends Employee> c) throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Employee employee) throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(String firstName, String secondName) throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }
}
