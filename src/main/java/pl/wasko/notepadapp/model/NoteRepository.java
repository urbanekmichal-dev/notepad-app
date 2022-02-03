package pl.wasko.notepadapp.model;

import java.util.List;

public interface NoteRepository {
    List<Note> findAll();
    Note findById(int id);
    boolean existsById(int id);
    Note save(Note note);
    void deleteById(int id);
}
