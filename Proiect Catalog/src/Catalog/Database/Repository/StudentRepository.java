package Catalog.Database.Repository;

import Catalog.Clasa;
import Catalog.Database.Config.DatabaseConfiguration;
import Catalog.Student;

import javax.swing.text.Style;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {


    public Student save(Student iStudent) {

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "INSERT into student(last_name, first_name, birth_date, id_clasa, nume_clasa) VALUES(?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, iStudent.getmLastName());
            preparedStatement.setString(2, iStudent.getmFistName());
            preparedStatement.setDate(3, new java.sql.Date(iStudent.getmBirthDate().getTime()));
            preparedStatement.setInt(4,  iStudent.getClasa().getId());
            preparedStatement.setString(5,  iStudent.getClasa().getName());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.close();
            return iStudent;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while saving the student: " + iStudent);
        }
    }
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "SELECT * FROM student";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                students.add(mapToStudent(resultSet));
            }
            resultSet.close();
            return students;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while tying to get all students: ");
        }
    }
    public Student findById(int id) {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "SELECT * FROM student where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Student student = (mapToStudent(resultSet));
            resultSet.close();
            return student;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while tying to get the student with id: " + id);
        }
    }
    public List <Student> getAllStudentsFromAClass (Clasa iClasa) {
        List <Student> students = new ArrayList<>();

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "SELECT * from student where id_clasa = ? and nume_clasa = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, iClasa.getId());
            preparedStatement.setString(2, iClasa.getName());

            ResultSet resultSet =  preparedStatement.executeQuery();
            while (resultSet.next()) {
                students.add(mapToStudent(resultSet));
            }

            resultSet.close();
            return students;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong getting all students from a class");
        }
    }

    public void remove (String iLast_name, String iFirst_name) {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "delete from student where last_name = ?  and first_name = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, iLast_name);
            preparedStatement.setString(2, iFirst_name);

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.close();

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while deleting the student: "
                                        + iLast_name + " " + iFirst_name );
        }
    }
    private Student mapToStudent(ResultSet resultSet) throws SQLException {
        Clasa tmpCls = new Clasa (resultSet.getInt(5), resultSet.getString(6));
        Student student = new Student(resultSet.getString(2), resultSet.getString(3),
                                      resultSet.getDate(4), tmpCls);
        return student;
    }
}
