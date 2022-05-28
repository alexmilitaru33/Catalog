package Catalog;

enum AllSubjects {
    Romana,
    Matematica,
    Informatica,
    Istorie,
    Geografie,
    Biologie
}
public class Subject { // an object that stores for an professor what subject he teaches
    private Professor professor;
    private AllSubjects subject;

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public AllSubjects getSubject() {
        return subject;
    }

    public void setSubject(AllSubjects subject) {
        this.subject = subject;
    }

    public Subject(Professor iProfessor, AllSubjects iSubject) {
        this.professor = iProfessor;
        this.subject = iSubject;
    }

    @Override
    public String toString() {
        return subject + " with " + professor;
    }
}
