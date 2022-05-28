package Catalog;
public class ManagerService {
    private static ManagerService instance = null;

    public static ManagerService getInstance() {
        if (instance == null)
            instance = new ManagerService();
        return  instance;
    }

    public void addClasa (Manager mgr, Clasa iClasa) {
        mgr.getClasses().add(iClasa);
    }

    public void addProfessor (Manager mgr, Professor prof) {
        mgr.getProfessors().add(prof);
    }

    public Professor getProfessor(Manager mgr, String iLastName, String iFirstName){
        for (Professor prof : mgr.getProfessors())
            if (prof.getmLastName().equals(iLastName) && prof.getmFistName().equals(iFirstName))
                return prof;
        return null;
    }

    public Clasa getClasaByName(Manager mgr, Integer id, String name) {
        for (Clasa clasa: mgr.getClasses())
            if (clasa.getId().equals(id) && clasa.getName().equals(name))
                return  clasa;
        return null;
    }

}
