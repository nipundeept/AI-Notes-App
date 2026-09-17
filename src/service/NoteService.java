package service;

import model.Note;
import java.util.ArrayList;

public class NoteService {
    private ArrayList<Note> storage;
    public NoteService() {
        storage = new ArrayList<>();
    }
    public void addNote(int id, String title, String content) {
        Note note = new Note(id, title, content);
        storage.add(note);
    }
    //simple get method
    public ArrayList<Note> getAllNotes() {
        //return storage; //if user accesses this, our original storage will be compromised if he does .clear(), instead we return a copy of the storage
        return new ArrayList<>(storage);
    }
    public Note getNoteById(int inputId) {
        //search for the id
        for (Note note : storage) {
            if (note.getId() == inputId) {
                return note;
            }
        }
        return null;
    }
    public boolean deleteNote(int id) {
        /*for (Note note : storage) {
            if (note.getId() == id) {
                storage.remove(note); //ConcurrentModificationException
                return true;
            }

        }
        return false;*/
        //do not repeat the logic when you can reuse an existing method
        Note current = getNoteById(id);
        if (current != null) {
            storage.remove(current);
            return true;
        }
        return false;
    }

    public boolean updateNote(int id, String newTitle, String newContent) {
        Note note = getNoteById(id);
        if (note != null) {
            note.setTitle(newTitle);
            note.setContent(newContent);
            return true;
        }
        return false;
    }
}
