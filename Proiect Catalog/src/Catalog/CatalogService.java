package Catalog;

import java.util.*;

public class CatalogService {
    private Map<Student, Map<Subject, ArrayList <Integer>> > grades;

    public CatalogService() {
        grades = new HashMap<>();
    }

    public void showGrades (Student iStudent) {

        ArrayList<Integer> listOfGrades;
        for (Map.Entry mapElement : grades.get(iStudent).entrySet()) {
            Subject key = (Subject) mapElement.getKey();
            System.out.println("Subject " + key);
            listOfGrades = grades.get(iStudent).get(key);

            for (Integer grade : listOfGrades) {
                System.out.print(grade + " ");
            }
            System.out.println();
        }
    }
    public void addNote (Student iStudent, Subject iSubject, Integer iGrade) {

        if (!grades.containsKey(iStudent)) {

            ArrayList<Integer> arrayOfGrades = new ArrayList<>();
            arrayOfGrades.add(iGrade);
            Map<Subject, ArrayList<Integer>> mapWithGrades = new HashMap<>();
            mapWithGrades.put(iSubject, arrayOfGrades);
            grades.put(iStudent, mapWithGrades);
        } else if (!grades.get(iStudent).containsKey(iSubject)) {

            ArrayList<Integer> arrayOfGrades = new ArrayList<>();
            arrayOfGrades.add(iGrade);
            grades.get(iStudent).put(iSubject, arrayOfGrades);
        }
        else {

            ArrayList<Integer> arrayOfGrades = grades.get(iStudent).get(iSubject);
            arrayOfGrades.add(iGrade);
        }

    }
    public Double calcAverage (Student iStudent, Subject iSubject) {
        try{
           ArrayList<Integer> listOfNotes = grades.get(iStudent).get(iSubject);
           double sum  = 0;
           for (Integer grade : listOfNotes) {
               sum += grade;
           }
           return sum/listOfNotes.size();
        }
        catch (Exception e ) {
            return  -1.0;
        }
    }

}
