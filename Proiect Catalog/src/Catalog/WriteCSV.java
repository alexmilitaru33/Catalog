package Catalog;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class WriteCSV {
    private static WriteCSV write_instance = null;

    private String baseURL = "C:/Users/milit/OneDrive/Desktop/FACULTATE UNIBUC/Sem 2/Java/Proiect Catalog/src/Catalog/CSV_file/";
    public static WriteCSV getInstance() {
        if (write_instance == null)
            write_instance = new WriteCSV();

        return write_instance;
    }

    public void writeClase(){
        Manager manager = Manager.getInstance();
        ArrayList<Clasa> classes = manager.getClasses();
        try {
            FileWriter csvWriter = new FileWriter(baseURL + "Clase.csv");
            csvWriter.append("Id,Nume\n");
            for (Clasa class_ : classes) {
                Integer id = class_.getId();
                String name = class_.getName();
                csvWriter.append(id + "," + name + "\n");
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeProfessors(){
        Manager manager = Manager.getInstance();
        ArrayList<Professor> professors = manager.getProfessors();
        try {
            FileWriter csvWriter = new FileWriter(
                    baseURL + "Professor.csv");
            csvWriter.append("First name,Last name,Birthdate,Salary\n");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            for (Professor prof : professors) {
                String fistName = prof.getmFistName();
                String lastName = prof.getmLastName();
                String bday = formatter.format(prof.getmBirthDate());
                Integer salary = prof.getSalary();
                csvWriter.append(fistName + "," + lastName + "," + bday + "," + salary + "\n");
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeSubjects(){
        Manager manager = Manager.getInstance();
        ArrayList<Clasa> classes = manager.getClasses();

        try {
            FileWriter csvWriter = new FileWriter(
                    baseURL + "SubjectsForClasses.csv");
            csvWriter.append("Id clasa,Nume clasa,First name prof,last name prof,Subject\n");
            for (Clasa clasa : classes) {
                ArrayList<Subject> subjects = clasa.getSubjects();
                Integer id_clasa = clasa.getId();
                String nume_clasa = clasa.getName();
                for (Subject subj : subjects) {
                    String f_name_prof = subj.getProfessor().getmFistName();
                    String l_name_prof = subj.getProfessor().getmLastName();
                    String subject = String.valueOf(subj.getSubject());
                    csvWriter.append(id_clasa + "," + nume_clasa + "," + f_name_prof + ',' +
                            l_name_prof + ',' + subject + '\n');
                }
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeStudents() {
        Manager manager = Manager.getInstance();
        ArrayList<Clasa> classes = manager.getClasses();

        try {
            FileWriter csvWriter = new FileWriter(
                      baseURL + "Students.csv");
            csvWriter.append("Last Name,First Name,Birthdate,Id clasa,Nume clasa\n");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            for (Clasa clasa : classes) {
                Set<Student> students = clasa.getStudents();
                Integer id = clasa.getId();
                String name = clasa.getName();
                for (Student stud : students) {
                    String last_name = stud.getmLastName();
                    String fist_name = stud.getmFistName();
                    String bday = formatter.format(stud.getmBirthDate());
                    csvWriter.append(last_name + ',' + fist_name + ',' + bday + ',' +
                                     id + ',' + name + '\n');
                }

            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
