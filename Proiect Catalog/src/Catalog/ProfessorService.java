package Catalog;

import Catalog.Database.Repository.ProfessorRepository;

import java.util.List;

public class ProfessorService {
    public ProfessorRepository repository = new ProfessorRepository();

    public void add (Professor iProfessor){
        if (iProfessor.getmLastName() != null && iProfessor.getmFistName() != null) {
            repository.save(iProfessor);
        }
        else
            throw new RuntimeException("Bad request!");
    }

    public List<Professor> getAll() {
        return  repository.findAll();
    }

    public void delete (Professor iProfessor) {
        if (iProfessor.getmLastName() != null && iProfessor.getmFistName() != null) {
            repository.remove(iProfessor.getmLastName(), iProfessor.getmFistName());
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
