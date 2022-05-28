package Catalog;

import java.util.Date;

public class Student extends Person {
    private Clasa clasa;

    public Student(String iLastName, String iFirstName, Date iBirthDate, Clasa iClasa) {
        super(iFirstName, iLastName, iBirthDate);
        this.clasa = iClasa;
        clasa.addStudent(this);

    }

    public Clasa getClasa() {
        return clasa;
    }

    public void setClasa(Clasa clasa) {
        this.clasa = clasa;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
