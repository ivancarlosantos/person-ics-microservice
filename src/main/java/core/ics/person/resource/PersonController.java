package core.ics.person.resource;

import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.ics.person.controller.methodes.PersonControllerMethodes;
import core.ics.person.dto.PersonDTO;
import core.ics.person.model.Person;
import core.ics.person.service.PersonService;

@RestController
@RequestMapping(path = "/api/person")
public class PersonController implements PersonControllerMethodes{

	@Autowired
	private PersonService personService;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	@PostMapping(path = "/save")
	public ResponseEntity<PersonDTO> save(@RequestBody Person persons) {
		Person personSaved = personService.personSave(persons);
		PersonDTO dto = mapper.map(personSaved, PersonDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@Override
	@GetMapping(path = "/list-all")
	public ResponseEntity<Collection<Person>> listAll(Person filter) {
		Collection<Person> list = personService.listAll(filter);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@Override
	@PutMapping(path = "/update/{id}")
	public ResponseEntity<PersonDTO> update(@PathVariable Long id, @RequestBody Person persons) {
		Person personUpdate = personService.update(id, persons);
		PersonDTO dto = mapper.map(personUpdate, PersonDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@Override
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@DeleteMapping(path = "/delete")
	public ResponseEntity<?> delete(@PathVariable Long id, Person person) {
		// TODO Auto-generated method stub
		return null;
	}

}
