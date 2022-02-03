package pl.wasko.notepadapp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
 interface SqlNoteRepository extends NoteRepository,JpaRepository<Note,Integer> {
}
