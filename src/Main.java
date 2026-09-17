import model.Note;
import service.NoteService;
public class Main {
    public static void main(String[] args) {

        NoteService service = new NoteService();
        service.addNote(1, "DBMS", "Learning queries");
        service.addNote(2, "Java", "Implementing a project in Java");
        service.addNote(3, "Music", "I like Daft Punk music");
        for (Note note : service.getAllNotes()) {
            System.out.println("Note ID : " + note.getId());
            System.out.println("Title : " + note.getTitle());
            System.out.println("Content : " + note.getContent());
        }
        service.updateNote(3, "Music", "I like Michael Jackson music");
        System.out.println("After updating : ");
        for (Note note : service.getAllNotes()) {
            System.out.println("Note ID : " + note.getId());
            System.out.println("Title : " + note.getTitle());
            System.out.println("Content : " + note.getContent());
        }
        service.deleteNote(2);
        System.out.println("After deletion: ");
        for (Note note : service.getAllNotes()) {
            System.out.println("Note ID : " + note.getId());
            System.out.println("Title : " + note.getTitle());
            System.out.println("Content : " + note.getContent());
        }
    }
}