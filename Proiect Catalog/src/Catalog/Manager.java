package Catalog;

import java.util.ArrayList;

public class Manager {
    private static ArrayList <Clasa> classes;
    private static ArrayList <Professor> professors;

    private static Manager manager = null;
    private Manager ()
    {
        classes = new ArrayList<>();
        professors = new ArrayList<>();
    }

    public static Manager getInstance () {
        if (manager == null)
            manager = new Manager();
        return  manager;
    }

    public ArrayList <Clasa> getClasses () {
        return classes;
    }

    public ArrayList <Professor> getProfessors () {
        return professors;
    }

    public void showProfessors () {

        int index = 1;
        for(Professor professor : professors) {
            System.out.println(index + ") " + professor);
            index += 1;
        }
        System.out.println();
    }

    public Clasa getClasa(Clasa iClasa) {
        for (Clasa clasa: classes) {
            if (clasa.equals(iClasa))
                return clasa;
        }
        return  null;
    }
}
