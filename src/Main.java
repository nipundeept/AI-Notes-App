import model.Note;
import repository.NoteRepository;
import service.NoteService;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        NoteRepository repository = new NoteRepository();
        NoteService service = new NoteService(repository);
        service.displayAllNotes();

    }
}