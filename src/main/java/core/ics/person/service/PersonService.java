package core.ics.person.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.ics.person.dto.PersonDTO;
import core.ics.person.enums.PersonStatus;
import core.ics.person.model.Address;
import core.ics.person.model.GenerateToken;
import core.ics.person.model.Person;
import core.ics.person.repository.PersonRepository;
import core.ics.person.service.methodes.PersonServiceMethodes;
import core.ics.person.utils.AddressRequest;
import core.ics.person.utils.Token;

@Service
public class PersonService implements PersonServiceMethodes {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private Token tokenizer;
	
	@Autowired
	private AddressRequest addressRequest;

	@Override
	@Transactional
	public Person personSave(Person person) {
		
		Optional<Person> launchPerson = personRepository.fetchByName(person.getPersonName()); 
		if (launchPerson.isPresent()) {
			throw new RuntimeException("Pessoa já cadastrada");
		}
		GenerateToken token = tokenizer.generateToken();
		Address address = addressRequest.requestCEP(person.getAddress());

		person.setAddress(address.toString());
		person.setToken(token.getToken());
		person.setStatus(PersonStatus.ACTIVE);
		person.setRegisterDate(LocalDateTime.now());

		return personRepository.saveAndFlush(person);
	}

	@Override
	public List<Person> listAll(Person filter) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<Person> example = Example.of(filter, matcher);

		List<Person> list = personRepository.findAll(example).stream()
				.filter(p -> p.getStatus().equals(PersonStatus.ACTIVE)).collect(Collectors.toList());

		return list;
	}
	
	public List<PersonDTO> list(){
		return personRepository.findAll().stream().map(p->new PersonDTO(p)).collect(Collectors.toList());
	}
	
	public Optional<Person> finPersonByID(Long id) {
		return personRepository.findById(id);
	}

	@Override
	public Optional<Person> fetchName(String name) {
		Optional<Person> fetchName = personRepository.fetchByName(name);
		if (!fetchName.isPresent()) {
			Optional.empty();
		}

		return fetchName;
	}

	public Optional<Person> fetchAddress(String cep) {
		Optional<Person> fetch = personRepository.fetchAddress(cep);
		if (fetch.isEmpty() || !fetch.isPresent()) {
			throw new RuntimeException("CEP "+HttpStatus.NOT_FOUND);
		}

		return fetch;
	}

	private Person findID(Long id){
		Optional<Person> findID = personRepository.findById(id);
		Person person = null;
		if (!findID.isPresent()){
			throw new RuntimeException("Person not found");
		}
		person = findID.get();

		return person;
	}

	@Override
	public Person update(Long id, Person oldPerson) {

		GenerateToken accessKey = tokenizer.generateToken();
		Person newPerson = findID(id);

		newPerson.setPersonName(oldPerson.getPersonName());
		newPerson.setCpf(oldPerson.getCpf());
		newPerson.setAddress(oldPerson.getAddress());
		newPerson.setGender(oldPerson.getGender());

		if (accessKey != null) {
			newPerson.setToken(accessKey.getToken());
		}
		newPerson.setModifyDate(LocalDateTime.now());
		newPerson.setStatus(oldPerson.getStatus());

		return personRepository.save(newPerson);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		if (id == null) {
			throw new RuntimeException("Error");
		}
		personRepository.deleteById(id);
	}

	@Override
	public void delete(Long id, Person person) {
		//Deprecaated
	}

}
