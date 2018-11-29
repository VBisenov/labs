package humanResources;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ProjectManager implements GroupsManager, Serializable {
    private LinkedList<EmployeeGroup> groups = new LinkedList<>();
    private int size;

    public ProjectManager(){
    }

    public ProjectManager(EmployeeGroup[] employeeGroup){
        groups.addAll(employeeGroup);
    }

    private boolean alreadyAdded(EmployeeGroup groupToAdd){
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).equals(groupToAdd)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return groups.size();
    }

    @Override
    public boolean isEmpty() {
        return groups.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return groups.contains(o);
    }

    @Override
    public Iterator<EmployeeGroup> iterator() {
        return groups.iterator();
    }

    @Override
    public Object[] toArray() {
        return groups.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return groups.toArray(a);
    }

    public boolean add(EmployeeGroup group) {
        if (group == null) return false;

        groups.add(group);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return groups.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return groups.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends EmployeeGroup> c) {
        return groups.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends EmployeeGroup> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return groups.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return groups.removeAll(c);
    }

    @Override
    public void clear() {
        groups.clear();
    }

    @Override
    public EmployeeGroup get(int index) {
        return groups.get(index);
    }

    @Override
    public EmployeeGroup set(int index, EmployeeGroup element) {
        return groups.set(index, element);
    }

    @Override
    public void add(int index, EmployeeGroup element) {
        groups.add(index, element);
    }

    @Override
    public EmployeeGroup remove(int index) {
        return groups.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return groups.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return groups.lastIndexOf(o);
    }

    @Override
    public ListIterator<EmployeeGroup> listIterator() {
        return groups.listIterator();
    }

    @Override
    public ListIterator<EmployeeGroup> listIterator(int index) {
        return groups.listIterator(index);
    }

    @Override
    public List<EmployeeGroup> subList(int fromIndex, int toIndex) {
        return groups.subList(fromIndex, toIndex);
    }

    public int groupsQuantity(){
        int count = 0;
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i) != null){
                count++;
            }
        }
        return count;
    }

    public int remove(EmployeeGroup group) {
        int count = 0;
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getName().equals(group.getName())) {
                groups.remove(i);
                count++;
            }
        }
        return count;
    }

    public boolean remove(String groupName){
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getName().equals(groupName)){
                groups.remove(i);
                return true;
            }
        }
        return false;
    }

    public EmployeeGroup getGroup(String groupName){
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getName().equals(groupName)){
                return groups.get(i);
            }
        }
        return null;
    }

    public EmployeeGroup[] getGroups(){
        EmployeeGroup[] temp = new EmployeeGroup[groupsQuantity()];
        for (int i = 0; i < groups.size(); i++) {
            temp[i] = groups.get(i);
        }
        return temp;
    }

    public int employeesQuantity(){
        int count = 0;
        for (int i = 0; i < groupsQuantity(); i++) {
            count += groups.get(i).getSize();
        }
        return count;
    }

    public int employeesQuantity(JobTitlesEnum jobTitle){
        int count = 0;
        for (int i = 0; i < groupsQuantity(); i++) {
            count += groups.get(i).getEmployees(jobTitle).length;
        }
        return count;
    }

    public Employee employeeBestSalary(){
        Employee temp = null;
        int indexOfMax = 0;
        for (int i = 0; i < groupsQuantity(); i++) {
            if (groups.get(i).employeeBestSalary().getSalary() < groups.get(indexOfMax).employeeBestSalary().getSalary()){
                indexOfMax = i;
                temp = groups.get(indexOfMax).employeeBestSalary();
            }
        }
        return temp;
    }

    public EmployeeGroup groupOfEmployee(String firstName, String secondName){
        EmployeeGroup temp = null;
        for (int i = 0; i < groupsQuantity(); i++) {
            if (groups.get(i).getEmployee(firstName, secondName) != null){
                temp = groups.get(i);
            }
        }
        return temp;
    }

    @Override
    public int partTimeEmployeesQuantity() {
        int count = 0;
        for (int i = 0; i < groups.size(); i++) {
            count += groups.get(i).partTimeEmployeesQuantity();
        }
        return count;
    }

    @Override
    public int staffEmployeeQuantity() {
        int count = 0;
        for (int i = 0; i < groups.size(); i++) {
            count += groups.get(i).staffEmployeesQuantity();
        }
        return count;
    }

    @Override
    public int employeesOnTripQuantity() {
        int count = 0;
        for (int i = 0; i < groups.size(); i++) {
            count += groups.get(i).employeesOnTripCurrentlyQuantity();
        }
        return count;
    }

    @Override
    public Employee[] employeesOnTrip() {
        Employee[] temp = new Employee[employeesOnTripQuantity()];
        for (int i = 0; i < groups.size(); i++) {
            temp = groups.get(i).employeesOnTripCurrently();
        }
        return temp;
    }

    @Override
    public double averageDaysInTrip() {
        double temp = 0;
        for (int i = 0; i < groups.size(); i++) {
            temp += groups.get(i).averageDaysInTrip();
        }
        return temp;
    }
}
