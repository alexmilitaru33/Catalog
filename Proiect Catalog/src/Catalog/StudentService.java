package Catalog;

import Catalog.Database.Repository.ClassRepository;
import Catalog.Database.Repository.StudentRepository;

import java.util.List;

public class StudentService {
    public StudentRepository repository = new StudentRepository();

    public void add (Student iStudent){
        if (iStudent.getmLastName() != null && iStudent.getmFistName() != null) {
            repository.save(iStudent);
        }
        else
            throw new RuntimeException("Bad request!");
    }

    public List<Student> getAll() {
        return  repository.findAll();
    }

    public void delete (Student iStudent) {
        if (iStudent.getmLastName() != null && iStudent.getmFistName() != null) {
            repository.remove(iStudent.getmLastName(), iStudent.getmFistName());
        }
        else
            throw new RuntimeException("Bad request!");
    }
    public void delete (String iLastName, String iFirstName) {
        if (iLastName != null && iFirstName != null) {
            repository.remove(iLastName, iFirstName);
        }
        else
            throw new RuntimeException("Bad request!");
    }
}
