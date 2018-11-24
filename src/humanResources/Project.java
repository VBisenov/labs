package humanResources;

import java.util.*;

public class Project implements EmployeeGroup {
    private LinkedList<Employee> employees = new LinkedList<>();
    private String name;

    public Project(String name){
        this.name = name;
    }

    public Project(String name, Employee[] employees){
        this.name = name;
        this.employees.addAll(employees);
    }

    public int getSize(){
        return employees.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private boolean alreadyAdded(Employee employeeToAdd){
        for (int i = 0; i < employees.size(); i++) {
            if (employeeToAdd.equals(employees.get(i))){
                return true;
            }
        }
        return false;
    }

    public Employee getEmployee(String firstName, String secondName){
        Employee employee;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getFirstName().equals(firstName) && employees.get(i).getSecondName().equals(secondName)){
                employee = employees.get(i);
                return employee;
            }
        }
        return null;
    }


    public boolean remove(String firstName, String secondName) {
        if (getEmployee(firstName, secondName) == null) {
            return false;
        } else {
            employees.remove(getEmployee(firstName, secondName));
            return true;
        }
    }

    public boolean remove(Employee employee) {
        if (employee == null) {
            return false;
        } else {
            employees.remove(employee);
            return true;
        }
    }

    public Employee employeeBestSalary() {
        int indexOfMax = 0;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getSalary() > employees.get(indexOfMax).getSalary()){
                indexOfMax = i;
            }
        }
        return employees.get(indexOfMax);
    }

    public int employeesQuantity(){
        int count = 0;
        for (int i = 0; i < employees.size(); i++) {
            count++;
        }
        return count;
    }

    public Employee[] getEmployees(){
        Employee[] array = new Employee[employees.size()];
        for (int i = 0; i < employees.size(); i++) {
            array[i] = employees.get(i);
        }
        return array;
    }

    private int employeesJobTitleQuantity(JobTitlesEnum jobTitle){
        int count = 0;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getJobTitle() == jobTitle){
                count++;
            }
        }
        return count;
    }

    public Employee[] getEmployees(JobTitlesEnum jobTitle){
        Employee[] temp = new Employee[employeesJobTitleQuantity(jobTitle)];
        int count = 0;
        for (int i = 0; i < temp.length; i++) {
            if (employees.get(i).getJobTitle() == jobTitle){
                temp[count++] = employees.get(i);
            }
        }
        return temp;
    }

    public Employee[] employeesSortedBySalary(){
        Employee[] result = getEmployees();
        Arrays.sort(result, Employee::compareTo);
        return result;
    }

//    public Employee[] employeesSortedBySalary(){
//        Employee[] result = getEmployees();
//        Employee temp;
//        for (int i = 0; i < employees.getSize() - 1; i++) {
//            for (int j = 0; j < employees.getSize() - 1; j++) {
//                if (result[j].getSalary() < result[j+1].getSalary()){
//                    temp = result[j];
//                    result[j] = result[j+1];
//                    result[j+1] = temp;
//                }
//            }
//        }
//        return result;
//    }

    public boolean contains(String firstName, String secondName){
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getFirstName().equals(firstName) && employees.get(i).getSecondName().equals(secondName)){
                return true;
            }
        }
        return false;
    }

    private JobTitlesEnum[] jobTitles(){
        JobTitlesEnum[] jobTitlesEnums = new JobTitlesEnum[employees.size()];
        for (int i = 0; i < employees.size(); i++) {
            jobTitlesEnums[i] = employees.get(i).getJobTitle();
        }
        return jobTitlesEnums;
    }


    @Override
    public int partTimeEmployeesQuantity() {
        int count = 0;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i) instanceof  PartTimeEmployee){
                count ++;
            }
        }
        return count;
    }

    @Override
    public int staffEmployeesQuantity() {
        int count = 0;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i) instanceof  StaffEmployee){
                count ++;
            }
        }
        return count;
    }


    @Override
    public int employeesOnTripCurrentlyQuantity() {
        int count = 0;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i) instanceof StaffEmployee) {
                if (((StaffEmployee) employees.get(i)).isOnTrip()) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public Employee[] employeesOnTripCurrently() {
        int count = 0;
        Employee[] temp = new Employee[employeesOnTripCurrentlyQuantity()];
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i) instanceof StaffEmployee) {
                if ((((StaffEmployee) employees.get(i)).isOnTrip())) {
                    temp[count++] = employees.get(i);
                }
            }
        }
        return temp;
    }

    @Override
    public double averageDaysInTrip() {
        double result;
        double employeesQuantity = employees.size();
        double daysCount = 0;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i) instanceof StaffEmployee && employees.get(i).getTravelsQuantity() > 0){
                for (int j = 0; j < ((StaffEmployee) employees.get(i)).getTravelsArray().length; j++) {
                    daysCount += ((StaffEmployee) employees.get(i)).getTravelsArray()[j].getDaysCount();
                }
            }
        }
        result = daysCount/employeesQuantity;
        return result;
    }

    @Override
    public int size() {
        return employees.size();
    }

    @Override
    public boolean isEmpty() {
        return employees.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return employees.contains(o);
    }

    @Override
    public Iterator<Employee> iterator() {
        return employees.iterator();
    }

    @Override
    public Object[] toArray() {
        return employees.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return employees.toArray(a);
    }

    public boolean add(Employee employee) {
        if (employee == null) {
            return false;
        }
        employees.add(employee);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return employees.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return employees.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Employee> c) {
        return employees.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Employee> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return employees.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return employees.removeAll(c);
    }

    @Override
    public void clear() {
        employees.clear();
    }



    @Override
    public Employee get(int index) {
        return employees.get(index);
    }

    @Override
    public Employee set(int index, Employee element) {
        return employees.set(index, element);
    }

    @Override
    public void add(int index, Employee element) {
        employees.add(index, element);
    }

    @Override
    public Employee remove(int index) {
        return employees.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return employees.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return employees.lastIndexOf(o);
    }

    @Override
    public ListIterator<Employee> listIterator() {
        return employees.listIterator();
    }

    @Override
    public ListIterator<Employee> listIterator(int index) {
        return employees.listIterator(index);
    }

    @Override
    public List<Employee> subList(int fromIndex, int toIndex) {
        return employees.subList(fromIndex, toIndex);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!name.equals("")) sb.append(" Name: ").append(name).append(" Size: ").append(employees.size());
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i) != null) {
                sb.append(employees.get(i));
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        Project project = (Project) obj;
        return name.equals(project.getName()) && Arrays.equals(getEmployees(), project.getEmployees());
    }

    @Override
    public int hashCode() {
        return name.hashCode() ^ Arrays.hashCode(getEmployees());
    }
}
