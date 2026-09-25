package service;

import model.Note;

import repository.NoteRepository;

import java.util.ArrayList;
import java.util.List;


public class NoteService {
    private NoteRepository repository;
    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }
    public void addNote(int id, String title, String content) {
        Note note = new Note(id, title, content);
        repository.save(note);
    }
    //simple get method
    public List<Note> getAllNotes() {
        //return storage; //if user accesses this, our original storage will be compromised if he does .clear(), instead we return a copy of the storage
        return repository.getAllNotes();
    }
    public Note getNoteById(int inputId) {
        return repository.getNoteById(inputId);
    }

    public void displayAllNotes() {
        List<Note> noteList = getAllNotes();
        int notesLength = noteList.size();
        for (Note note : noteList) {
            System.out.println("ID : " +note.getId() + " " + "Title : " + note.getTitle());
            System.out.println(note.getContent());
            System.out.println();
        }
    }
    public boolean deleteNote(int id) {
        return repository.deleteNoteById(id);
    }

    public boolean updateNote(int id, String newTitle, String newContent) {
       return repository.updateNote(id, newTitle, newContent);
    }
}
