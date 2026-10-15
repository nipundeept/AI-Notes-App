package service;

import model.Note;

import repository.NoteRepository;

import java.sql.SQLException;
import java.util.List;


public class NoteService {
    private NoteRepository repository;
    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }
    public void addNote(int id, String title, String content) throws SQLException {
        Note note = new Note(id, title, content);
        repository.save(note);
    }
    //simple get method
    public List<Note> getAllNotes() throws SQLException {
        //return storage; //if user accesses this, our original storage will be compromised if he does .clear(), instead we return a copy of the storage
        return repository.getAllNotes();
    }

    public Note getNoteById(int inputId) throws SQLException {
        return repository.getNoteById(inputId);
    }

    public void displayAllNotes() throws SQLException {
        List<Note> noteList = getAllNotes();
        for (Note note : noteList) {
            System.out.println("ID : " +note.getId() + " " + "Title : " + note.getTitle());
            System.out.println(note.getContent());
            System.out.println();
        }
    }
    public boolean deleteNote(int id) throws SQLException {
        return repository.deleteNoteById(id);
    }

    public boolean updateNote(int id, String newTitle, String newContent) throws SQLException {
       return repository.updateNote(id, newTitle, newContent);
    }
}
