package repository;

import model.Note;

import config.DatabaseConnection;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class NoteRepository {

    public void save(Note note) throws SQLException {
        //Create the SQL String
        String sql = "INSERT INTO notes (id, title, content) VALUES (?, ?, ?)"; // ? - Placeholders
        //get the connection and use prepared statement using try-with-resources
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, note.getId());
            statement.setString(2, note.getTitle());
            statement.setString(3, note.getContent());
            statement.executeUpdate();
        }
    }

    public List<Note> getAllNotes() throws SQLException {
            //query database
        String sql = "SELECT * FROM NOTES";
        List<Note> notes = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {//gets the rows returned by MySQL
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");

                Note note = new Note(id, title, content);
                notes.add(note);
            }
        }

        return notes;
    }

    public Note getNoteById(int id) throws SQLException {
        String sql = "SELECT * FROM notes WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String title = resultSet.getString("title");
                    String content = resultSet.getString("content");
                    return new Note(id, title, content);
                }
            }
        }
        return null;
    }

    public boolean deleteNoteById(int id) throws SQLException {
        String sql = "DELETE FROM notes WHERE ID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            return (rowsAffected > 0); // Note deletion was successfull in this case
        }
    }

    public boolean updateNote(int id, String newTitle, String newContent) throws SQLException {
        String sql = "UPDATE notes SET title = ?, content = ? WHERE id = ?";

        int rowsAffected;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newTitle);
            statement.setString(2, newContent);
            statement.setInt(3, id);

            rowsAffected = statement.executeUpdate();

        }

        return (rowsAffected > 0);
    }

}
