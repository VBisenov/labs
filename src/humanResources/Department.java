package humanResources;

import java.util.*;

public class Department implements EmployeeGroup{
    private String name;
    private Employee[] employees;
    private int size;
    private static final int DEFAULT_CAPACITY = 8;

    public Department(String name) {
        this(name, DEFAULT_CAPACITY);
    }

    public Department(String name, int capacity) {
        if (capacity < 0) throw new NegativeSizeException("Field capacity must be positive");
        this.name = name;
        this.employees = new Employee[capacity];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getSize() {
        return size;
    }

    public Department(String name, Employee[] employees) {
        this.name = name;
        this.size = employees.length;
        this.employees = new Employee[employees.length]; // возможно лучше будет this.employees = employees;
        System.arraycopy(employees, 0, this.employees, 0, employees.length);
    }

//    private boolean alreadyAdded(Employee employeeToAdd) {
//        for (Employee employee : employees) {
//            if (employee != null) {
//                if (employeeToAdd.equals(employee)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

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
        for (int i = 0; i < employees.length; i++) {
            if (o.equals(employees[i])){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Employee> iterator() {
        return new Iterator<Employee>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < employees.length;
            }

            @Override
            public Employee next() {
                if (!hasNext()){
                    throw new NoSuchElementException();
                }
                return employees[index++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return getEmployees();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        return (T[]) toArray();
    }

    public boolean add(Employee employeeToAdd){
        if (employeeToAdd == null) return false;

        if (employees.length == size) {
            Employee[] employeesBuff = new Employee[size * 2];
            System.arraycopy(employees, 0, employeesBuff, 0, employees.length);
            employees = employeesBuff;
        }
        employees[size++] = employeeToAdd;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(employees[i])){
                System.arraycopy(employees, i+1, employees,i, employees.length-1-i);
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                if (c.toArray()[j].equals(employees[j])){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Employee> c) {
        for (int i = 0; i < c.toArray().length-1; i++) {
            if (!(c.toArray()[i] instanceof Employee)) {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Employee> c) {
        int count = index;
        for (int i = 0; i < size; i++) {
            System.arraycopy(employees, index, employees, index+c.size(), size-index);
            add(count++, employees[i]);
            size += c.size();
            //todo придумать условие выхода из цикла
        }
        return false;
    }
    /*
    ^^^
    Копируем элементы из employee[index] в employee[index+c.size], количество size-index, добавляем в index++ из коллекции
     */

    @Override
    public boolean retainAll(Collection<?> c) {
        for(Employee employee: employees){
            if (!c.contains(employee)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Employee employee: employees){
            if (c.contains(employee)){
                remove(employee);
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for(Employee employee: employees){
            remove(employee);
        }
    }

    public boolean remove(String firstName, String secondName) {
        for (int i = 0; i < employees.length - 1; i++) {
            if (employees[i].getFirstName().equals(firstName) && employees[i].getSecondName().equals(secondName)) {
                System.arraycopy(employees, i + 1, employees, i, employees.length - 1 - i);
                employees[employees.length - 1 - i] = null;
                size--;
                return true;
            }
        }
        return false;
    }


    public int employeesQuantity() {
        int count = 0;
        for (Employee employee : employees) {
            if (employee != null)
                count++;
        }
        return count;
    }

    public Employee[] getEmployees() {
        Employee[] employeesTemp = new Employee[size];
        for (int i = 0; i < size; i++) {
            employeesTemp[i] = employees[i];
        }
        return employeesTemp;
    }


    private int employeesJobTitleQuantity(JobTitlesEnum jobTitle) {
        int count = 0;
        for (Employee employee : employees) {
            if (employee != null) {
                if (employee.getJobTitle().equals(jobTitle)) {
                    count++;
                }
            }
        }
        return count;
    }

    public Employee[] getEmployees(JobTitlesEnum jobTitle) {
        Employee[] employeesTemp = new Employee[employeesJobTitleQuantity(jobTitle)];
        int count = 0;
        for (Employee employee : employees) {
            if (employee != null) {
                if (employee.getJobTitle().equals(jobTitle)) {
                    employeesTemp[count++] = employee;
                }
            }
        }
        return employeesTemp;
    }

    public Employee[] employeesSortedBySalary(){
        Employee[] result = getEmployees();
        Arrays.sort(result, Employee::compareTo);
        return result;
    }

//    public Employee[] employeesSortedBySalary() {
//        Employee temp;
//        for (int i = size-1; i > 0; i--) {
//            for (int j = 0; j < i ; j++) {
//                if (employees[j].getSalary() > employees[j+1].getSalary()){
//                    temp = employees[j];
//                    employees[j] = employees[j+1];
//                    employees[j+1] = temp;
//                }
//            }
//        }
//        return employees;
//    }

    public boolean contains(String firstName, String secondName){
        for (Employee employee: getEmployees()) {
            if (employee.getFirstName().equals(firstName) && employee.getSecondName().equals(secondName)){
                return true;
            }
        }
        return false;
    }


    //3 lab:
    public boolean remove(Employee employee){
        for (int i = 0; i < size; i++) {
            if (employee == employees[i]){
                System.arraycopy(employees, i+1, employees, i, employees.length-1-i);
                size--;
                return true;
            }
        }
        return false;
    }

    public boolean remove(JobTitlesEnum jobTitle) {
        for (int j = 0; j < size; j++) {
            if (employees[j].getJobTitle() == jobTitle) {
                System.arraycopy(employees, j+1, employees, j, employees.length-1-j);
                size--;
                j--;  // уменьшаем j, т.к. при увеличении на 1 вылетает за пределы
            }
        }
        return false;
    }


    public Employee getEmployee(String firstName, String secondName){
        for (Employee employee: employees) {
            if (employee.getFirstName().equals(firstName) && employee.getSecondName().equals(secondName)){
                return employee;
            }
        }
        return null;
    }


    private JobTitlesEnum[] jobTitles(){ // создаём массив жобтайтлов
        JobTitlesEnum[] jobTitles = new JobTitlesEnum[size];
        for (int i = 0; i < size; i++) {
            jobTitles[i] = employees[i].getJobTitle();
        }
        return jobTitles;
    }

    public JobTitlesEnum[] jobTitlesWithoutRepeat(){
        JobTitlesEnum[] jobTitles = jobTitles();
        for (int i = 0; i < jobTitles().length-1; i++) {
            if (jobTitles[i] == jobTitles[i+1]){
                System.arraycopy(jobTitles, i+1, jobTitles, i, jobTitles.length-1-i);
                jobTitles[jobTitles.length-1] = null; // без нулов не получается:((
            }
        }
        return jobTitles;
    }


    public Employee employeeBestSalary() {
        int indexOfMax = 0;
        for (int i = 0; i < size; i++) {
            if (employees[i].getSalary() > employees[indexOfMax].getSalary()){
                indexOfMax = i;
            }
        }
        return employees[indexOfMax];
    }

    private int employeesWithBusinessTripQuantity(){
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (employees[i].getTravelsQuantity() > 0){
                count++;
            }
        }
        return count;
    }

    public Employee[] employeesWithBusinessTrip(){
        Employee[] temp = new Employee[employeesWithBusinessTripQuantity()];
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (employees[i].getTravelsQuantity() > 0){
                temp[count++] = employees[i];
            }
        }
        return temp;
    }

    @Override
    public int partTimeEmployeesQuantity() {
        int count = 0;
        for (int i = 0; i < employees.length - 1; i++) {
            if (employees[i] instanceof PartTimeEmployee) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int staffEmployeesQuantity() {
        int count = 0;
        for (int i = 0; i < employees.length - 1; i++) {
            if (employees[i] instanceof StaffEmployee) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int employeesOnTripCurrentlyQuantity() {
        int count = 0;
        for (int i = 0; i < employees.length-1; i++) {
            if (employees[i] instanceof StaffEmployee){
                if (((StaffEmployee) employees[i]).isOnTrip()){
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public Employee[] employeesOnTripCurrently() {
        Employee[] temp = new Employee[employeesOnTripCurrentlyQuantity()];
        int count = 0;
        for (int i = 0; i < employees.length-1; i++) {
            if (employees[i] instanceof StaffEmployee){
                if (((StaffEmployee) employees[i]).isOnTrip()){
                    temp[count++] = employees[i];
                }
            }
        }
        return temp;
    }


    @Override
    public double averageDaysInTrip() {
        double result;
        double employeesQuantity = 0;
        double daysCount = 0;
        for (Employee employee: employees) {
            if (employee != null) {
                employeesQuantity++;
            }
            if (employee instanceof StaffEmployee){
                for (int j = 0; j < ((StaffEmployee) employee).getTravelsArray().length; j++) {
                    daysCount += ((StaffEmployee) employee).getTravelsArray()[j].getDaysCount();
                }
            }
        }
        result = daysCount/employeesQuantity;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!name.equals("")) sb.append("Department: ").append(name);
        if (size != 0) sb.append(" size: "+size);
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                sb.append(employees[i]);
            }
        }
        return String.format(sb.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Department)) return false;

        Department department = (Department) obj;
        if (!department.getName().equals(name)) return false;
        if (department.employeesQuantity() != employeesQuantity()) return false;
        return Arrays.equals(department.employees, employees);
    }

    @Override
    public int hashCode() {
        return getName().hashCode() ^ size ^ Arrays.hashCode(employees);
    }

    @Override
    public Employee get(int index) {
        return employees[index];
    }

    @Override
    public Employee set(int index, Employee element) {
        employees[index] = element;
        return employees[index];
    }

    @Override
    public void add(int index, Employee element) {
        size++;
        System.arraycopy(employees, index, employees, index+1, size-index);
        employees[index] = element;
    }

    @Override
    public Employee remove(int index) {
        int count = 0;
        Employee result = null;
        for (Employee employee: employees){
            count++;
            if (count == index){
                result = employee;
                remove(employee);
            }
        }
        return result;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (employees[i].equals(o)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size; i < 0; i--) {
            if (employees[i].equals(o)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<Employee> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Employee> listIterator(int index) {
        return null;
    }

    @Override
    public List<Employee> subList(int fromIndex, int toIndex) {
        int count = fromIndex;
        List<Employee> list = new LinkedList<>();
        for (Employee employee: employees){
            while (count++ == toIndex) {
                list.add(fromIndex, employee);
            }
        }
        return list;
    }

}
