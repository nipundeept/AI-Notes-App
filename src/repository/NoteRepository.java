package repository;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import model.Note;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class NoteRepository {
    String url = "jdbc:mysql://localhost:3306/ai_notes";
    String username = System.getenv("DB_USERNAME");
    String password = System.getenv("DB_PASSWORD");

    public void testConnection() {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected successfully!");
            connection.close();
        } catch(Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }

    public void save(Note note) {
        //Create the SQL String
        String sql = "INSERT INTO notes (id, title, content) VALUES (?, ?, ?)"; // ? - Placeholders
        //get the connection and use prepared statement using try-with-resources
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, note.getId());
            statement.setString(2, note.getTitle());
            statement.setString(3, note.getContent());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Connection to the database failed!");
        }
    }

    public List<Note> getAllNotes() {
            //query database
        String sql = "SELECT * FROM NOTES";
        List<Note> notes = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()){//gets the rows returned by MySQL
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");

                Note note = new Note(id, title, content);
                notes.add(note);
            }

        } catch(Exception e) {
            System.out.println("ERROR!");
            e.printStackTrace();
        }

        return notes;
    }

    public Note getNoteById(int id) {
        String sql = "SELECT * FROM notes WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String title = resultSet.getString("title");
                    String content = resultSet.getString("content");
                    return new Note(id, title, content);
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR!");
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteNoteById(int id) {
        String sql = "DELETE FROM notes WHERE ID = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Note Deleted Successfully!");
                return true;
            }

        }
        catch (Exception e) {
            System.out.println("ERROR!");
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateNote(int id, String newTitle, String newContent) {
        String sql = "UPDATE notes SET title = ?, content = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newTitle);
            statement.setString(2, newContent);
            statement.setInt(3, id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Note Updated Successfully!");
                return true;
            }
        } catch (Exception e) {
            System.out.println("ERROR!");
            e.printStackTrace();
        }

        return false;
    }

}
