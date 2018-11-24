package humanResources;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DepartmentsManager implements GroupsManager {
    private String name;
    private EmployeeGroup[] employeeGroups;
    private int size;
    private static final int DEFAULT_SIZE = 8;

    public DepartmentsManager(String name) {
        this(name, new EmployeeGroup[DEFAULT_SIZE]);
    }

    public DepartmentsManager(String name, EmployeeGroup[] groups) {
        this.name = name;
        this.employeeGroups = new EmployeeGroup[groups.length];
        System.arraycopy(employeeGroups, 0, this.employeeGroups, 0, employeeGroups.length);
    }

    public int groupsQuantity(){
        int count=0;
        for (int i = 0; i < employeeGroups.length; i++) {
            if (employeeGroups[i] != null){
                count++;
            }
        }
        return count;
    }

    private boolean alreadyAdded(EmployeeGroup departmentToAdd) {
        for (EmployeeGroup group : employeeGroups) {
            if (group != null) {
                if (group.equals(departmentToAdd)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                if (o.equals(employeeGroups[j])){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Iterator<EmployeeGroup> iterator() {
        return new Iterator<EmployeeGroup>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < employeeGroups.length;
            }

            @Override
            public EmployeeGroup next() {
                return employeeGroups[index++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return employeeGroups;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        return ((T[]) toArray());
    }

    public boolean add(EmployeeGroup departmentToAdd) {
        if (departmentToAdd == null) return false;
        if (employeeGroups.length == size) {
            EmployeeGroup[] departmentsBuff = new EmployeeGroup[size * 2];
            System.arraycopy(employeeGroups, 0, departmentsBuff, 0, size);
            employeeGroups = departmentsBuff;
        }
        employeeGroups[size++] = departmentToAdd;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(employeeGroups[i])){
                System.arraycopy(employeeGroups, i+1, employeeGroups, i, employeeGroups.length-1-i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (int i = 0; i < size; i++) {
               if (c.contains(employeeGroups[i])){
                   return true;
               }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends EmployeeGroup> c) {
        for (int i = 0; i < c.toArray().length-1; i++) {
            add((EmployeeGroup) c.toArray()[i]);
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends EmployeeGroup> c) {
        int count = index;
        for (int i = 0; i < size; i++) {
            System.arraycopy(employeeGroups, index, employeeGroups, index+c.size(), size-index);
            add(count++, employeeGroups[i]);
            size += c.size();
            //todo придумать условие выхода из цикла
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (EmployeeGroup employeeGroup: employeeGroups){
            if (!c.contains(employeeGroup)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (EmployeeGroup employeeGroup: employeeGroups){
            if (c.contains(employeeGroup)){
                remove(employeeGroup);
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (EmployeeGroup employeeGroup: employeeGroups){
            remove(employeeGroup);
        }
    }

    @Override
    public EmployeeGroup get(int index) {
        return employeeGroups[index];
    }

    @Override
    public EmployeeGroup set(int index, EmployeeGroup element) {
        employeeGroups[index] = element;
        return employeeGroups[index];
    }

    @Override
    public void add(int index, EmployeeGroup element) {
        size++;
        System.arraycopy(employeeGroups, index, employeeGroups, index+1, size-index);
        employeeGroups[index] = element;
    }

    @Override
    public EmployeeGroup remove(int index) {
        int count = 0;
        EmployeeGroup result = null;
        for (EmployeeGroup group: employeeGroups){
            count++;
            if (count == index){
                result = group;
                remove(group);
            }
        }
        return result;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (employeeGroups.equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size; i < 0; i--) {
            if (employeeGroups.equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<EmployeeGroup> listIterator() {
        return null;
    }

    @Override
    public ListIterator<EmployeeGroup> listIterator(int index) {
        return null;
    }

    @Override
    public List<EmployeeGroup> subList(int fromIndex, int toIndex) {
        int count = fromIndex;
        List<EmployeeGroup> list = new LinkedList<>();
        for (EmployeeGroup group: employeeGroups){
            while (count++ == toIndex) {
                list.add(fromIndex, group);
            }
        }
        return list;
    }

    public boolean remove(String name) {
        for (int i = 0; i < size; i++) {
            if (employeeGroups[i].getName().equals(name)) {
                System.arraycopy(employeeGroups, i + 1, employeeGroups, i, employeeGroups.length - 1 - i);
                employeeGroups[employeeGroups.length - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    public int remove(EmployeeGroup employeeGroup){
        int count = 0;
        for (int i = 0; i < employeeGroup.getSize(); i++) {
            if (this.employeeGroups[i].equals(employeeGroup)){
                System.arraycopy(this.employeeGroups, i+1, this.employeeGroups, i, employeeGroup.getSize()-1-i);
                size--;
                count++;
            }
        }
        return count;
    }

    public EmployeeGroup getGroup(String name) {
        for (EmployeeGroup employeeGroup : employeeGroups) {
            if (employeeGroup.getName().equals(name)) {
                return employeeGroup;
            }
        }
        return null;
    }

    public EmployeeGroup[] getGroups() {
        EmployeeGroup[] departmentsTemp = new EmployeeGroup[size];
        for (int i = 0; i < size; i++) {
            departmentsTemp[i] = employeeGroups[i];
        }
        employeeGroups = departmentsTemp;
        return employeeGroups;
    }

    public int departmentsQuantity() {
        int count = 0;
        for (EmployeeGroup employeeGroup : employeeGroups) {
            if (employeeGroup != null) {
                count++;
            }
        }
        return count;
    }

    public int employeesQuantity(){
        int count = 0;
        for (EmployeeGroup employeeGroup : employeeGroups){
            count += employeeGroup.employeesQuantity();
        }
        return count;
    }

    public int employeesQuantity(JobTitlesEnum jobTitle){
        int count = 0;
        for (EmployeeGroup employeeGroup: employeeGroups){
            count += employeeGroup.getEmployees(jobTitle).length;
        }
        return count;
    }

    public Employee employeeBestSalary(){
        int indexOfMax = 0;
        for (int i = 0; i < employeesQuantity(); i++) {
            if (employeeGroups[i].employeeBestSalary().getSalary() > employeeGroups[indexOfMax].employeeBestSalary().getSalary()){
                indexOfMax = i;
            }
        }
        return employeeGroups[indexOfMax].employeeBestSalary();
    }


    public EmployeeGroup groupOfEmployee(String firstName, String secondName){
        EmployeeGroup departmentTemp = null;
        for (EmployeeGroup employeeGroup: employeeGroups){
            if (employeeGroup.contains(firstName,secondName)){
                departmentTemp = employeeGroup;
            }
        }
        return departmentTemp;
    }

    @Override
    public int partTimeEmployeesQuantity() {
        int count = 0;
        for (int i = 0; i < employeeGroups.length; i++) {
            for (EmployeeGroup employeeGroup: employeeGroups){
                count += employeeGroup.partTimeEmployeesQuantity();
            }
        }
        return count;
    }

    @Override
    public int staffEmployeeQuantity() {
        int count = 0;
        for (int i = 0; i < employeeGroups.length; i++) {
            for (EmployeeGroup employeeGroup: employeeGroups){
                count += employeeGroup.staffEmployeesQuantity();
            }
        }
        return count;
    }

    @Override
    public int employeesOnTripQuantity() {
        int count = 0;
        for (EmployeeGroup employeeGroup: employeeGroups){
            count = employeeGroup.employeesOnTripCurrentlyQuantity();
        }
        return count;
    }

    @Override
    public Employee[] employeesOnTrip() {
        Employee[] temp = new Employee[employeesOnTripQuantity()];
        for (EmployeeGroup employeeGroup: employeeGroups){
            temp = employeeGroup.employeesOnTripCurrently();
        }
        return temp;
    }

    @Override
    public double averageDaysInTrip() {
        double temp = 0;
        for (EmployeeGroup group: getGroups()) {
            temp += group.averageDaysInTrip();
        }
        return temp;
    }
}
