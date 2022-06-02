package core.ics.person.controller.methodes;

import java.util.List;

import org.springframework.http.ResponseEntity;

import core.ics.person.dto.PersonDTO;
import core.ics.person.model.Person;

public interface PersonControllerMethodes {

	public ResponseEntity<PersonDTO> save(Person persons);
	
	public ResponseEntity<List<Person>> listAll(Person filter);
	
	public ResponseEntity<PersonDTO> fetchName(String name);
	
	public ResponseEntity<PersonDTO> update(Long id, Person persons);
	
	public ResponseEntity<?> deleteById(Long id);
	
}
