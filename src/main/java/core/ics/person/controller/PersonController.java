package core.ics.person.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
import core.ics.person.doctype.Documentation;
import core.ics.person.dto.PersonDTO;
import core.ics.person.enums.Author;
import core.ics.person.model.Address;
import core.ics.person.model.Person;
import core.ics.person.service.PersonService;
import core.ics.person.utils.AddressRequest;

@RestController
@RequestMapping(path = "/api/person")
public class PersonController implements PersonControllerMethodes {

	@Autowired
	private PersonService personService;
	
	@Autowired
	private AddressRequest addressRequest;

	@Override
	@Documentation(author = Author.IVAN_SANTOS)
	@PostMapping(path = "/save")
	public ResponseEntity<PersonDTO> save(@RequestBody Person persons) {
		Address address = addressRequest.requestCEP(persons.getAddress());
		Person personSaved = personService.personSave(persons);
		PersonDTO dto = new PersonDTO(personSaved);
		dto.setAddress(address);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@Override
	@Documentation(author = Author.IVAN_SANTOS)
	@GetMapping(path = "/list-all")
	public ResponseEntity<List<Person>> listAll(Person filter) {
		List<Person> list = personService.listAll(filter);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@Documentation(author = Author.IVAN_SANTOS)
	@GetMapping(path = "/list")
	public ResponseEntity<List<PersonDTO>> list() {
		List<PersonDTO> list = personService.list();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@Documentation(author = Author.IVAN_SANTOS)
	@GetMapping(path = "/find-cep/{cep}")
	public ResponseEntity<PersonDTO> fetchAddress(@PathVariable String cep) {
		Optional<Person> person = personService.fetchAddress(cep);
		Address address = addressRequest.requestCEP(cep);
		PersonDTO dto = new PersonDTO(person.get());
		dto.setModifyDate(person.get().getModifyDate());
		dto.setAddress(address);
		dto.setToken(person.get().getToken());
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}


	@Documentation(author = Author.IVAN_SANTOS)
	@GetMapping(path = "/fetch/{named}")
	public ResponseEntity<PersonDTO> fetchName(@PathVariable(name = "named") String named) {
		Optional<Person> list = personService.fetchName(named);
		PersonDTO dto = new PersonDTO(list.get());
		Address address = addressRequest.requestCEP(list.get().getAddress());
		dto.setAddress(address);
		dto.setToken(list.get().getToken());
		dto.setModifyDate(list.get().getModifyDate());
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@Documentation(author = Author.IVAN_SANTOS)
	@GetMapping(path = "/test")
	public ResponseEntity<String> testConnection() throws UnknownHostException {
		String test = "Timestamp: " + LocalDateTime.now() + " Address: " + InetAddress.getLocalHost();	
		return ResponseEntity.status(HttpStatus.OK).body(test);
	}

	@Override
	@Documentation(author = Author.IVAN_SANTOS)
	@PutMapping(path = "/update/{id}")
	public ResponseEntity<PersonDTO> update(@PathVariable(name = "id") Long id, @RequestBody Person persons) {
		Person personUpdate = personService.update(id, persons);
		PersonDTO dto = new PersonDTO(personUpdate);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@Override
	@Documentation(author = Author.IVAN_SANTOS)
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable Long id) {
		personService.deleteById(id);
		return ResponseEntity.ok(HttpStatus.NO_CONTENT);
	}

	@Override
	@Documentation(author = Author.IVAN_SANTOS)
	@DeleteMapping(path = "/delete")
	public ResponseEntity<?> delete(@PathVariable Long id, Person person) {
		return null;
	}

}
