import model.Note;
import service.NoteService;
public class Main {
    public static void main(String[] args) {

        NoteService service = new NoteService();
        service.addNote(1, "DBMS", "Learning queries");
        service.addNote(2, "Java", "Implementing a project in Java");
        service.addNote(3, "Music", "I like Daft Punk music");
        for (Note note : service.getAllNotes()) {
            System.out.println(note.getId());
            System.out.println(note.getTitle());
            System.out.println(note.getContent());
        }
        service.deleteNote(1);
        System.out.println("After deletion the container looks like : ");
        for (Note note : service.getAllNotes()) {
            System.out.println(note.getId());
            System.out.println(note.getTitle());
            System.out.println(note.getContent());
        }
    }
}