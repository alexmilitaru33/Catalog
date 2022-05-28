package Catalog.Database.Repository;

import Catalog.Database.Config.DatabaseConfiguration;
import Catalog.Student;

import java.sql.*;

public class SubjectProjessorRepository {
    public void save(String professorLastName, String professorFirstname, String subject) {

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "INSERT into subject_professor(id_subject, id_professor) VALUES(" +
                    "       (select id from professor where last_name = ? and first_name = ?) ," +
                    "       (select id from subject where name = ?));";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, professorLastName);
            preparedStatement.setString(2, professorFirstname);
            preparedStatement.setString(3, subject);

            boolean ok = preparedStatement.execute();
            if (ok) {
                System.out.println("Operatiune realizata cu succes");
            } else {
                System.out.println("Operatie esuata");
            }

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while assigning a subject for a professor");
        }
    }
}
