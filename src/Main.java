import model.Note;

import java.sql.SQLException;
import java.util.List;
import repository.NoteRepository;
import service.NoteService;


public class Main {
    public static void main(String[] args) {
        NoteRepository repository = new NoteRepository();
        NoteService service = new NoteService(repository); // dependency injection

        try {
            //1. Read all notes
            List<Note> notes = service.getAllNotes();

            System.out.println("Displaying all the notes : ");

            for (Note note : notes) {
                System.out.println("ID : " + note.getId());
                System.out.println("Title : " + note.getTitle());
                System.out.println("Content : " + note.getContent());
                System.out.println("-----------------------------------------------------------------------");
            }

            boolean update = service.updateNote(2, "Strategy Games", "I love playing Chess");

            if (update) {
                System.out.println("Note updated successfully!");
            }
            else {
                System.out.println("Not update was not successfull!");
            }

            boolean deletion = service.deleteNote(2);

            if (deletion) {
                System.out.println("Note deleted successfully!");
            }
            else {
                System.out.println("Note not found!");
            }

            service.addNote(345, "The Weeknd", "I like songs of The Weeknd!");


        } catch (SQLException e) {
            System.out.println("Database operation failed : " + e.getMessage());
        }

    }
}