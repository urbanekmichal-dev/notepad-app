package pl.wasko.notepadapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wasko.notepadapp.AppConfigurationProperties;
import pl.wasko.notepadapp.model.Note;
import pl.wasko.notepadapp.model.NoteRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
public class NoteController {
    private final NoteRepository noteRepository;
    private final AppConfigurationProperties appConfigurationProperties;

    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getNotes(){
        return ResponseEntity.ok(noteRepository.findAll());
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getNote(@PathVariable int id){
        if(noteRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(noteRepository.findById(id));
    }
    @PostMapping("/notes")
    public ResponseEntity<Note> addNote(@RequestBody @Valid Note note){
        note.setPropertiesValue(appConfigurationProperties.getMyProperties());
        Note result = noteRepository.save(note);
        return ResponseEntity.created(URI.create("/"+note.getId())).body(result);
    }
    @PutMapping("/notes/{id}")
    public ResponseEntity<Note> updateNote(@RequestBody @Valid Note note, @PathVariable int id){
        if(noteRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Note result = noteRepository.findById(id);
        result.setContent(note.getContent());
        result.setTitle(note.getTitle());
        return ResponseEntity.ok( noteRepository.save(result));
    }
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable int id){
        if(noteRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        noteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
