package Catalog.Database.Config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupData {
    public void setup () {
        createClassTable();
        createStudentTable();
        createProfessorTable();
        createSubjectTable();
        createSubjectProfessor();
        createGradeTable();
    }

    private void createClassTable() {
        String query = "CREATE TABLE IF NOT EXISTS clasa (\n" +
                "    id INT,\n" +
                "    nume VARCHAR(255),\n" +
                "    PRIMARY KEY (id, nume)" +
                ")";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void createStudentTable() {
        String query = "CREATE TABLE IF NOT EXISTS student (\n" +
                "    id INT auto_increment,\n" +
                "    last_name VARCHAR(255),\n" +
                "    first_name VARCHAR(255),\n" +
                "    birth_date DATE,\n" +
                "    id_clasa INT,\n" +
                "    nume_clasa VARCHAR(255),\n" +
                "    PRIMARY KEY (id),\n" +
                "    FOREIGN KEY (id_clasa, nume_clasa) references clasa(id, nume),\n" +
                "    unique (last_name, first_name, birth_date)" +
                ")";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void createProfessorTable() {
        String query = "CREATE TABLE IF NOT EXISTS professor (\n" +
                "    id INT auto_increment,\n" +
                "    last_name VARCHAR(255),\n" +
                "    first_name VARCHAR(255),\n" +
                "    birth_date DATE,\n" +
                "    salary INT,\n" +
                "    PRIMARY KEY (id),\n" +
                "    unique (last_name, first_name, birth_date)" +
                ")";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createSubjectTable() {
        String query = "CREATE TABLE IF NOT EXISTS subject (\n" +
                "    id INT auto_increment,\n" +
                "    name VARCHAR(255),\n" +
                "    PRIMARY KEY (id)" +
                ")";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createSubjectProfessor() {
        String query = "CREATE TABLE IF NOT EXISTS subject_professor (\n" +
                "    id INT auto_increment,\n" +
                "    id_subject INT,\n" +
                "    id_professor INT,\n" +
                "    PRIMARY KEY (id)," +
                "    FOREIGN KEY (id_professor) references professor(id)," +
                "    FOREIGN KEY (id_subject) references subject(id)" +
                ")";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void createGradeTable() {
        String query = "CREATE TABLE IF NOT EXISTS grade (\n" +
                "    id INT auto_increment,\n" +
                "    id_student INT not null ,\n" +
                "    id_subject_prof INT not null,\n" +
                "    grade INT,\n" +
                "    PRIMARY KEY (id)," +
                "    FOREIGN KEY (id_subject_prof) references subject_professor(id)," +
                "    FOREIGN KEY (id_student) references student(id)" +
                ")";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
