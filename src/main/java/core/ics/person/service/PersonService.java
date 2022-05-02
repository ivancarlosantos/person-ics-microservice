package core.ics.person.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.ics.person.enums.PersonStatus;
import core.ics.person.model.Person;
import core.ics.person.repository.PersonRepository;
import core.ics.person.service.methodes.PersonServiceMethodes;

@Service
public class PersonService implements PersonServiceMethodes {

	@Autowired
	private PersonRepository personRepository;

	private String generateAccessKey(Person person) {

		UUID key = UUID.randomUUID();
		String accessKey = null;
		if (person.getAccessKey() == null) {
			accessKey = key.toString().toUpperCase();
		}
		return accessKey;
	}

	@Override
	@Transactional
	public Person personSave(Person person) {
		String accessKey = generateAccessKey(person);
		person.setAccessKey(accessKey);
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

	@Override
	public Optional<Person> fetchName(String name) {
		Optional<Person> findName = personRepository.fetchByName(name);
		if (!findName.isEmpty()) {
			//
		}else {
			Optional.empty();
		}
		
		return findName;
	}

	@Override
	@Transactional
	public Person update(Long id, Person oldPerson) {

		String accessKey = generateAccessKey(oldPerson);
		Person newPerson = personRepository.findById(id).get();

		newPerson.setNamed(oldPerson.getNamed());
		newPerson.setGender(oldPerson.getGender());
		if (accessKey != null) {
			oldPerson.setAccessKey(accessKey);
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
	@Transactional
	public void delete(Long id, Person person) {
		Optional<Person> findPerson = personRepository.findById(id);
		if (findPerson.isPresent()) {
			person = findPerson.get();
			personRepository.delete(person);
		} else {
			throw new RuntimeException("Error");
		}
	}
}
