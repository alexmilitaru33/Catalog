package Catalog;

import java.util.Date;

public class Professor extends Person{
    private Integer salary;

    public Professor(String iFirstName, String iLastName, Date iBirthDate, Integer salary) {
        super(iFirstName, iLastName, iBirthDate);
        this.salary = salary;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
