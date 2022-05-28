package Catalog;

import Catalog.Database.Config.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeService {
    public void addGrade(String lastNameStudent, String firstNameStudent, String iSubject, int grade) {
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {

            String query = "INSERT into grade(id_student, id_subject_prof, grade) VALUES(" +
                    "                       (select id from student where last_name = ? and first_name = ?)," +
                    "                       (select id from subject where name = ?), ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, lastNameStudent);
            preparedStatement.setString(2, firstNameStudent);
            preparedStatement.setString(3, iSubject);
            preparedStatement.setInt(4, grade);

            preparedStatement.execute();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//            resultSet.close();

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong adding a new grade");
        }
    }

    public List<Integer> showGrades(Student student, String subject) {
        List<Integer> gradeList= new ArrayList<>();
        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            String query = "select grade from grade, student, subject where student.last_name = ?" +
                    "                                    and student.first_name = ?  and student.id_clasa = ? " +
                    "                                    and student.nume_clasa = ?  and subject.name = ? ;";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, student.getmLastName());
            preparedStatement.setString(2, student.getmFistName());
            preparedStatement.setInt(3, student.getClasa().getId());
            preparedStatement.setString(4, student.getClasa().getName());
            preparedStatement.setString(5, subject);

            ResultSet resultSet =  preparedStatement.executeQuery();

            while(resultSet.next()) {
                gradeList.add(resultSet.getInt(1));
            }

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong adding a new grade");
        }
        return gradeList;
    }
}
