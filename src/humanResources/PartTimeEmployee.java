package humanResources;

public class PartTimeEmployee extends Employee {

    public PartTimeEmployee(String firstName, String secondName){
        super(firstName, secondName);
    }

    protected PartTimeEmployee(String firstName, String secondName, JobTitlesEnum jobTitle, int salary){
        super(firstName, secondName, jobTitle, salary);
    }

    @Override
    public int compareTo(Employee o) {
        return super.compareTo(o);
    }

    @Override
    int getTravelsQuantity() {
        return 0;
    }

    @Override
    int getBonus() {
        return 0;
    }

    @Override
    void setBonus(int bonus) {
        //nothing to do
    }


    @Override
    public String toString() {

        return String.format("%s %s, %s (part time employee), %d р. ", getSecondName(), getFirstName(), getJobTitle().toString(), getSalary());

        /*
        StringBuilder result = new StringBuilder();
        result.append(getSecondName()).append(" ").append(getFirstName());
        if (!getJobTitle().equals(JobTitlesEnum.NONE)) {
            result.append(", ").append(getJobTitle());
        }
        if (getSalary() != 0) {
            result.append(", ").append(getSalary()).append(" р.");
        }
        return result.toString();
        */
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof PartTimeEmployee)) {
            return false;
        }

        PartTimeEmployee emp = (PartTimeEmployee) obj;

        return (this.getSecondName().equals(emp.getSecondName()) && this.getFirstName().equals(emp.getFirstName())
                && this.getJobTitle().equals(emp.getJobTitle()) && this.getSalary() == emp.getSalary());
    }


    @Override
    public int hashCode() {
        return this.getSecondName().hashCode() ^ this.getFirstName().hashCode()
                ^ this.getJobTitle().hashCode() ^ this.getSalary();
    }

}
