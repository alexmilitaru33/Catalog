package Catalog.Database.Repository;

import Catalog.Database.Config.DatabaseConfiguration;
import Catalog.Clasa;
import Catalog.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ClassRepository {

    public Clasa save(Clasa clasa) {

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "INSERT into clasa(id, nume) VALUES(?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, clasa.getId());
            preparedStatement.setString(2, clasa.getName());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                clasa.setId(resultSet.getInt(1));
            }
            resultSet.close();
            return clasa;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while saving the class: " + clasa);
        }
    }
    public List<Clasa> findAll() {
        List<Clasa> classes = new ArrayList<>();
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "SELECT * FROM clasa";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                classes.add(mapToClasa(resultSet));
            }

            resultSet.close();
            return classes;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while tying to get all classes: ");
        }
    }

    public void remove (Clasa clasa) {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "delete from clasa where id = ? and nume = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, clasa.getId());
            preparedStatement.setString(2, clasa.getName());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.close();

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while deleting the class: " + clasa);
        }
    }
    private Clasa mapToClasa(ResultSet resultSet) throws SQLException {
        Clasa clasa = new Clasa(resultSet.getInt(1), resultSet.getString(2));
        return clasa;
    }
}
