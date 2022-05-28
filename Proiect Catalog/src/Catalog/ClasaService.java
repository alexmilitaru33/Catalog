package Catalog;

import Catalog.Database.Repository.ClassRepository;
import Catalog.Database.Repository.StudentRepository;

import java.util.List;

public class ClasaService {
    public static ClasaService instance = null;
    private ClassRepository repository = new ClassRepository();
    private StudentRepository studentRepository = new StudentRepository();

    public void add (Clasa clasa){
        if (clasa.getId() != null && clasa.getName() != null) {
            repository.save(clasa);
        }
        else
            throw new RuntimeException("Bad request!");
    }

    public List<Clasa> getAll() {
        return  repository.findAll();
    }

    public void delete (Clasa clasa) {
        if (clasa.getId() != null && clasa.getName() != null) {
            repository.remove(clasa);
        }
        else
            throw new RuntimeException("Bad request!");
    }
    public static ClasaService getInstance() {
        if (instance == null)
            instance = new ClasaService();
        return instance;
    }

    public List<Student> getAllStudents(Clasa iClasa) {
        if (iClasa.getName() != null ) {
            return studentRepository.getAllStudentsFromAClass(iClasa);
        }
        else
            throw new RuntimeException("Bad request!");
    }
    public void showStudents(Clasa cls) {
        int index = 1;
        for (Student student : cls.getStudents()) {
            System.out.println(index + ") " + student);
            index += 1;
        }
        System.out.println();
    }

    public void showSubjects (Clasa cls) {
        int index = 1;
        System.out.println("The subjects for " + cls.getId() + cls.getName() + " are: " );
        for (Subject subject : cls.getSubjects()) {
            System.out.println(index + ") " + subject);
            index += 1;
        }
        System.out.println();
    }

    public void showGrades(Clasa cls,Student iStudent) {
        cls.getCatalog().showGrades(iStudent);
    }

    public  void calcAverage (Clasa cls, Student iStudent, Subject iSubject) {
        cls.getCatalog().calcAverage(iStudent, iSubject);
    }

    public void addGrade(Clasa clasa, Student iStudent, AllSubjects iSubject, Integer iNota) {
        Subject subject = null;
        for (Subject item : clasa.getSubjects()) {
            if (item.getSubject() == iSubject)
                subject = item;
        }
        if (subject == null)
            System.out.println("This subject not exist");
        else
            clasa.getCatalog().addNote(iStudent, subject, iNota);
    }

    public void addSubjects(Clasa clasa, Professor iProfessor, AllSubjects iSubj) {

        boolean existSubj = false;
        for (Subject item : clasa.getSubjects()) {
            if (item.getSubject() == iSubj) {
                existSubj = true;
                break;
            }
        }
        if (!existSubj) {
            Subject sub = new Subject(iProfessor, iSubj);
            clasa.getSubjects().add(sub);
        }

    }

    public Student getStudent(Clasa clasa, String iLastName, String iFirstName) {
        for (Student student : clasa.getStudents())
            if (student.getmFistName().equals(iFirstName) && student.getmLastName().equals(iLastName))
                return student;
        return null;
    }
}
