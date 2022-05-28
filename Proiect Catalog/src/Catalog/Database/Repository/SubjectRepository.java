package Catalog.Database.Repository;

import Catalog.Clasa;
import Catalog.Database.Config.DatabaseConfiguration;
import Catalog.Student;
import Catalog.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepository {
    public void save(String iName) {

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "INSERT into subject(name) VALUES(?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, iName);

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.close();

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while saving the subject: " + iName);
        }
    }
    public List<String> findAll() {
        List<String> students = new ArrayList<>();
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "SELECT * FROM subject";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                students.add(resultSet.getString(2));
            }
            resultSet.close();
            return students;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while tying to get all subjects: ");
        }
    }
    public void remove (String iName) {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "delete from subject where name = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, iName);

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.close();

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while deleting the subjects: " + iName);
        }
    }

}
