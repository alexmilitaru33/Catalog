package Catalog.Database.Repository;

import Catalog.Database.Config.DatabaseConfiguration;
import Catalog.Professor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorRepository {
    public Professor save(Professor iProfessor) {

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "INSERT into professor(last_name, first_name, birth_date, salary) VALUES(?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, iProfessor.getmLastName());
            preparedStatement.setString(2, iProfessor.getmFistName());
            preparedStatement.setDate(3, new java.sql.Date(iProfessor.getmBirthDate().getTime()));
            preparedStatement.setInt(4,  iProfessor.getSalary());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.close();
            return iProfessor;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while saving the professor: " + iProfessor);
        }
    }
    public List<Professor> findAll() {
        List<Professor> professors = new ArrayList<>();
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "SELECT * FROM professor";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                professors.add(mapToProfessor(resultSet));
            }
            resultSet.close();
            return professors;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while tying to get all professors: ");
        }
    }

    public void remove (String iLast_name, String iFirst_name) {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "delete from professor where last_name = ?  and first_name = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, iLast_name);
            preparedStatement.setString(2, iFirst_name);

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.close();

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while deleting the professor: "
                    + iLast_name + " " + iFirst_name );
        }
    }
    private Professor mapToProfessor(ResultSet resultSet) throws SQLException {
        Professor professor = new Professor(resultSet.getString(2), resultSet.getString(3),
                resultSet.getDate(4), resultSet.getInt(5));
        return professor;
    }
}
