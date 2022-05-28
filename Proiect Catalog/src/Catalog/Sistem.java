package Catalog;

import Catalog.Database.Config.SetupData;
import Catalog.Database.Repository.ProfessorRepository;
import Catalog.Database.Repository.StudentRepository;
import Catalog.Database.Repository.SubjectProjessorRepository;
import Catalog.Database.Repository.SubjectRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Sistem {

    public Clasa readClasa() {
        Scanner scanner = new Scanner(System.in);
        Integer classId;
        String className;
        System.out.println("Enter class id: ");
        classId = scanner.nextInt();
        System.out.println("Enter class name: ");
        className = scanner.next();
        return new Clasa(classId, className);
    }
    Sistem() {
    }

    public void menu() throws IOException {
        Manager manager = Manager.getInstance();
        Student student;
        String lastName;
        String firstName, name;
        Date bDay;
        Clasa clasa;
        Professor teacher;
        Integer salary, grade;
        Integer nota;
        int chosen;

        initValues(manager);
        Scanner scanner = new Scanner(System.in);
        WriteCSV writeCSV = WriteCSV.getInstance();
        LogCSV logCSV = LogCSV.getInstance();

        ClasaService clasaService = ClasaService.getInstance();
        StudentService studentService = new StudentService();
        ProfessorService professorService = new ProfessorService();
        ManagerService managerService = ManagerService.getInstance();
        GradeService gradeService = new GradeService();
        SubjectRepository subjectRepository = new SubjectRepository();
        SubjectProjessorRepository subjectProjessorRepository = new SubjectProjessorRepository();


        SetupData setupData = new SetupData();
        setupData.setup();

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1) Add a new class");
            System.out.println("2) Add a new student");
            System.out.println("3) Add a new teacher");
            System.out.println("4) Add a grade");
            System.out.println("5) Show the students from a class");
            System.out.println("6) Show all teachers");
            System.out.println("7) Show subjects");
            System.out.println("8) Show grades for a student");
            System.out.println("9) Add a subject");
            System.out.println("10) Show all classes");
            System.out.println("11) Delete a class");
            System.out.println("12) Delete a student");
            System.out.println("13) Delete a professor");
            System.out.println("14) Assign a subject for a professor");


            System.out.println("\nEnter your option ");
            chosen = scanner.nextInt();
            System.out.println(chosen);
            try {
            switch (chosen) {
                case 1:
                    System.out.println("     ----- Add a new class -----");
                    clasa = readClasa();
                    managerService.addClasa(manager, clasa);
                    clasaService.add(clasa);
                    LogCSV.Log("Add a new class");
                    break;
                case 2:
                    System.out.println("     ----- Add a new student -----");

                    System.out.println("Enter the last name for the student");
                    lastName = scanner.next();
                    System.out.println("Enter the first name for the student");
                    firstName = scanner.next();
                    System.out.println("Enter the birth date for the student");
                    bDay = new Date(scanner.next());
                    System.out.println("Enter the class where you want to add the user:");
                    clasa = readClasa();
                    student = new Student(lastName, firstName, bDay, clasa);
                    studentService.add(student);

                    manager.getClasa(clasa).addStudent(student);
                    LogCSV.Log("Add a new student");
                    break;

                case 3:
                    System.out.println("     ----- Add a new teacher -----");
                    System.out.println("Enter the last name:");
                    lastName = scanner.next();
                    System.out.println("Enter the first name: ");
                    firstName = scanner.next();
                    System.out.println("Enter the birth date:");
                    bDay = new Date(scanner.next());
                    System.out.println("Enter the salary: ");
                    salary = scanner.nextInt();
                    teacher = new Professor(lastName, firstName, bDay, salary);
                    professorService.add(teacher);
                    managerService.addProfessor(manager, teacher);
                    LogCSV.Log("Add a new teacher");
                    break;

                case 4:
                    System.out.println("     ----- Add Grade -----");
                    System.out.println("Enter the last name:");
                    lastName = scanner.next();
                    System.out.println("Enter the first name:");
                    firstName = scanner.next();
                    System.out.println("Enter the subject:");
                    String subj = scanner.next();
                    System.out.println("Enter the grade:");
                    grade = scanner.nextInt();
                    nota = grade;
                    gradeService.addGrade(lastName, firstName, subj, grade);
//                    student = clasaService.getStudent(manager.getClasa(clasa), lastName, firstName);
//                    clasaService.addGrade(manager.getClasa(clasa), student, subj, nota);
//                    clasaService.showGrades(manager.getClasa(clasa), student);
                    LogCSV.Log("Add grade");
                    break;

                case 5:
                    System.out.println("     ----- Show Students -----");
                    clasa = readClasa();
                    System.out.println("The students are:");

                    for (Student student1 : clasaService.getAllStudents(clasa))
                        System.out.println(student1);
//                    clasaService.showStudents(manager.getClasa(clasa));
                    LogCSV.Log("Show students");
                    break;

                case 6:
                    System.out.println("     ----- Show Teachers -----");
                    System.out.println("List of professors:");
                    List<Professor> professorList = professorService.getAll();
                    for(Professor professor : professorList) {
                        System.out.println(professor);
                    }
                    System.out.println();
//                    manager.showProfessors();
                    LogCSV.Log("Show teachers");
                    break;

                case 7:
                    System.out.println("     ----- Show Subjects ------");
                    int index = 1;
                    for (String subject : subjectRepository.findAll()) {
                        System.out.println(index + ") " + subject);
                        index += 1;
                    }
//                    clasaService.showSubjects(manager.getClasa(clasa));
                    LogCSV.Log("Show subjects");
                    break;

                case 8:
                    System.out.println("     ----- Show the grades for a student -----");
                    System.out.println("Enter the last name:");
                    lastName = scanner.next();
                    System.out.println("Enter the first name:");
                    firstName = scanner.next();
                    clasa = readClasa();
                    System.out.println("Enter the subject:");
                    String subject = scanner.next();
                    student = clasaService.getStudent(manager.getClasa(clasa), lastName, firstName);
//                    clasaService.showGrades(manager.getClasa(clasa), student);
                    System.out.println(subject + " notes");
                    for (Integer elem : gradeService.showGrades(student, subject)) {
                        System.out.print(elem + ", ");
                    }
                    System.out.println();
                    LogCSV.Log("Show grades for a student");
                    break;

                case 9:
                    System.out.println("     ----- Add a new subject -----");
                    System.out.println("Enter the name ");
                    name = scanner.next();
                    subjectRepository.save(name);
                    break;


                case 10:
                    System.out.println("All classes:");
                    int index_cls = 1;
                    for (Clasa elem : clasaService.getAll()) {
                        System.out.println(index_cls + ") " + elem);
                        index_cls++;
                    }
                    System.out.println();
                    LogCSV.Log("Show all classes");
                    break;

                case 11:
                    System.out.println("     ----- Remove a class -----");
                    clasa = readClasa();
                    clasaService.delete(clasa);
                    LogCSV.Log("Delete a class");
                    break;

                case 12:
                    System.out.println("     ----- Remove a student -----");
                    System.out.println("Enter the last name: ");
                    lastName = scanner.next();
                    System.out.println("Enter the first name: ");
                    firstName = scanner.next();
                    studentService.delete(lastName, firstName);
                    break;

                case 13:
                    System.out.println("     ----- Remove a professor -----");
                    System.out.println("Enter the last name: ");
                    lastName = scanner.next();
                    System.out.println("Enter the first name: ");
                    firstName = scanner.next();
                    professorService.delete(lastName, firstName);
                    break;

                case 14 :
                    System.out.println("     ----- Assign a subject for a professor -----");
                    System.out.println("Enter the last name:");
                    lastName = scanner.next();
                    System.out.println("Enter the first name: ");
                    firstName = scanner.next();
                    System.out.println("Enter the subject name: ");
                    subject = scanner.next();
                    subjectProjessorRepository.save(lastName, firstName, subject);

                default:
                    writeCSV.writeClase();
                    writeCSV.writeProfessors();
                    writeCSV.writeSubjects();
                    writeCSV.writeStudents();
                    return;
            }
            }
            catch (Exception e) {
                System.out.println("Invalid input, try again");
            }
        }
    }
    void initValues (Manager manager) {
        ReadCSV readCSV = ReadCSV.getInstance();
        ClasaService clasaService = ClasaService.getInstance();
        ManagerService managerService = ManagerService.getInstance();

        ArrayList<Clasa> clasa_list = readCSV.readClasa();
        for (Clasa clasa : clasa_list)
            managerService.addClasa(manager, clasa);

        ArrayList<Professor> professor_list = readCSV.readProfessors();
        for (Professor prof : professor_list)
            managerService.addProfessor(manager, prof);

        ArrayList<Student> student_list = readCSV.readStudent();
        ArrayList<Subject> readSubject = readCSV.readSubject();


    }
}
