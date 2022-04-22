package core.ics.person.controller.methodes;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import core.ics.person.dto.PersonDTO;
import core.ics.person.model.Person;

public interface PersonControllerMethodes {

	public ResponseEntity<PersonDTO> save(Person persons);
	
	public ResponseEntity<Collection<Person>> listAll(Person filter);
	
	public ResponseEntity<PersonDTO> update(Long id, Person persons);
	
	public ResponseEntity<?> deleteById(Long id);
	
	public ResponseEntity<?> delete(Long id, Person person);
}
