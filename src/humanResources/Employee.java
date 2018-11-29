package humanResources;

import java.io.Serializable;

public abstract class Employee implements Comparable<Employee>, Serializable {
    private String firstName, secondName;
    private JobTitlesEnum jobTitle;
    private int salary;
    private static final int DEFAULT_SALARY = 0;

    protected Employee(String firstName, String secondName) {
        this(firstName, secondName, JobTitlesEnum.NONE, DEFAULT_SALARY);
    }

    protected Employee(String firstName, String secondName, JobTitlesEnum jobTitle, int salary) {
        if (salary < 0) throw new IllegalArgumentException("Field salary must be positive");
        this.firstName = firstName;
        this.secondName = secondName;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public JobTitlesEnum getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitlesEnum jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public int compareTo(Employee o) {
        return (getSalary()+getBonus() - o.getSalary()+o.getBonus());
    }

    public abstract int getTravelsQuantity();

    public abstract int getBonus();

    public abstract void setBonus(int bonus);


    @Override
    public String toString() {
        return String.format("%s %s %s %d р. ", getSecondName(), getFirstName(), getJobTitle().toString(), getSalary());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Employee)) {
            return false;
        }

        Employee emp = (Employee) obj;
        return (emp.jobTitle.equals(this.jobTitle) && (emp.salary == this.salary) &&
                emp.firstName.equals(this.firstName) && emp.secondName.equals(this.secondName));
    }

    @Override
    public int hashCode() {
        return getFirstName().hashCode() ^ getSecondName().hashCode() ^ getJobTitle().hashCode() ^ salary;
    }
}
