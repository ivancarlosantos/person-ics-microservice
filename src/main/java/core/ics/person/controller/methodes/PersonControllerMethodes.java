package core.ics.person.controller.methodes;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import core.ics.person.dto.PersonDTO;
import core.ics.person.model.Person;

public interface PersonControllerMethodes {

	public ResponseEntity<PersonDTO> save(Person persons);
	
	public ResponseEntity<List<Person>> listAll(Person filter);
	
	public ResponseEntity<Optional<Person>> fetchName(String name);
	
	public ResponseEntity<PersonDTO> update(Long id, Person persons);
	
	public ResponseEntity<?> deleteById(Long id);
	
	public ResponseEntity<?> delete(Long id, Person person);
}
